package varianttombstones.theswitch.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.option.GameSettings;
import net.minecraft.client.option.enums.ParticlesQuality;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.particle.ParticleSmoke;
import net.minecraft.client.render.particle.ParticleWaterDrop;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.block.material.Materials;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.sound.SoundCategory;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.world.World;
import net.minecraft.core.world.biome.Biome;
import net.minecraft.core.world.weather.IPrecipitation;
import net.minecraft.core.world.weather.Weather;
import net.minecraft.core.world.weather.Weathers;
import org.joml.Vector3d;
import org.joml.Vector3dc;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
	@Unique
	private final Random random = new Random();

	@Unique
	private int rainSoundCounter = 0;

	@Shadow
	private int tick;

	@Shadow
	public Minecraft mc;

	@Inject(
		method = "addRainParticles",
		at = @At("HEAD"),
		cancellable = true
	)
	private void stopBloodRainGroundParticles(CallbackInfo ci)
	{
		if (GameSettings.PARTICLES_QUALITY.value != ParticlesQuality.NONE) {
			World world = mc.currentWorld;
			if (world != null) {
				Weather weather = world.getCurrentWeather();
				if (weather instanceof IPrecipitation) {
					if (weather == Weathers.OVERWORLD_RAIN_BLOOD)
					{
						IPrecipitation precipitation = (IPrecipitation)weather;
						if (precipitation.spawnRainParticles()) {
							float rainLevel = world.getWeatherManager().getWeatherIntensity() * world.getWeatherManager().getWeatherPower();
							if (GameSettings.PARTICLES_QUALITY.value == ParticlesQuality.REDUCED) {
								rainLevel /= 2.0F;
							}

							if (rainLevel == 0.0F) {
								return;
							}

							this.random.setSeed((long) this.tick * 312987231L);
							int cameraX = MathHelper.floor(this.mc.activeCamera.getX());
							int cameraY = MathHelper.floor(this.mc.activeCamera.getY());
							int cameraZ = MathHelper.floor(this.mc.activeCamera.getZ());
							byte weatherDiameter = 10;
							double rainParticlePositionX = (double) 0.0F;
							double rainParticlePositionY = (double) 0.0F;
							double rainParticlePositionZ = (double) 0.0F;
							int spawnedParticles = 0;

							for (int ii = 0; ii < (int) (100.0F * rainLevel * rainLevel); ++ii) {
								int x = cameraX + this.random.nextInt(10) - this.random.nextInt(10);
								int z = cameraZ + this.random.nextInt(10) - this.random.nextInt(10);
								int heightmapPosition = world.findTopSolidBlock(x, z);
								int blockId = world.getBlockId(x, heightmapPosition - 1, z);
								Biome biome = world.getBlockBiome(x, heightmapPosition, z);
								if (!biome.blockedWeathers.contains(weather) && heightmapPosition <= cameraY + 10 && heightmapPosition >= cameraY - 10) {
									float blockX = this.random.nextFloat();
									float blockZ = this.random.nextFloat();
									if (blockId != Blocks.AIR.id()) {
										if (Blocks.blocksList[blockId].getMaterial() == Materials.LAVA) {
											this.mc.particleEngine.add(new ParticleSmoke(world, (double) ((float) x + blockX), (double) ((float) heightmapPosition + 0.1F) - Blocks.blocksList[blockId].getBounds().minY(), (double) ((float) z + blockZ), (double) 0.0F, (double) 0.0F, (double) 0.0F, 1.0F, 0));
										} else {
											++spawnedParticles;
											if (this.random.nextInt(spawnedParticles) == 0) {
												rainParticlePositionX = (double) ((float) x + blockX);
												rainParticlePositionY = (double) ((float) heightmapPosition + 0.1F) - Blocks.blocksList[blockId].getBounds().minY();
												rainParticlePositionZ = (double) ((float) z + blockZ);
											}
										}
									}
								}
							}

							if (spawnedParticles > 0 && this.random.nextInt(3) < this.rainSoundCounter++) {
								this.rainSoundCounter = 0;
								float volume;
								float pitch;
								if (rainParticlePositionY > this.mc.activeCamera.getY() + (double) 1.0F && world.getHeightValue(MathHelper.floor(this.mc.activeCamera.getX()), MathHelper.floor(this.mc.activeCamera.getZ())) > MathHelper.floor(this.mc.activeCamera.getY())) {
									volume = 0.1F;
									pitch = 0.5F;
								} else {
									volume = 0.2F;
									pitch = 1.0F;
								}

								this.mc.currentWorld.playSoundEffect((Entity) null, SoundCategory.WEATHER_SOUNDS, rainParticlePositionX, rainParticlePositionY, rainParticlePositionZ, "theswitch:ambient.weather.bloodrain", volume * this.mc.currentWorld.getWeatherManager().getWeatherIntensity() * this.mc.currentWorld.getWeatherManager().getWeatherPower() * 0.5F, pitch);
							}
						}
						ci.cancel();
					}
				}
			}
		}

	}
}
