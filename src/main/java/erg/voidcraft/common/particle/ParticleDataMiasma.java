package erg.voidcraft.common.particle;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import erg.voidcraft.common.init.VoidcraftParticles;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nonnull;
import java.awt.*;
import java.util.Locale;

//https://github.com/TheGreyGhost/MinecraftByExample/tree/master/src/main/java/minecraftbyexample/mbe50_particle

public class ParticleDataMiasma implements IParticleData {

    public ParticleDataMiasma(Color tint, double diameter) {
        this.tint = tint;
        this.diameter = constrainDiameterToValidRange(diameter);
    }

    public Color getTint() {
        return tint;
    }

    /**
     * @return get diameter of particle in metres
     */
    public double getDiameter() {
        return diameter;
    }

    @Nonnull
    @Override
    public ParticleType<ParticleDataMiasma> getType() {
        return VoidcraftParticles.miasmaParticleType;
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeInt(tint.getRed());
        buf.writeInt(tint.getGreen());
        buf.writeInt(tint.getBlue());
        buf.writeDouble(diameter);
    }

    @Nonnull
    @Override
    public String getParameters() {
        return String.format(Locale.ROOT, "%s %.2f %i %i %i",
                this.getType().getRegistryName(), diameter, tint.getRed(), tint.getGreen(), tint.getBlue());
    }

    private static double constrainDiameterToValidRange(double diameter) {
        final double MIN_DIAMETER = 0.05;
        final double MAX_DIAMETER = 1.0;
        return MathHelper.clamp(diameter, MIN_DIAMETER, MAX_DIAMETER);
    }

    private Color tint;
    private double diameter;

    public static final Codec<ParticleDataMiasma> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    Codec.INT.fieldOf("tint").forGetter(d -> d.tint.getRGB()),
                    Codec.DOUBLE.fieldOf("diameter").forGetter(d -> d.diameter)
            ).apply(instance, ParticleDataMiasma::new)
    );

    private ParticleDataMiasma(int tintRGB, double diameter) {
        this.tint = new Color(tintRGB);
        this.diameter = constrainDiameterToValidRange(diameter);
    }

    public static final IDeserializer<ParticleDataMiasma> DESERIALIZER = new IDeserializer<ParticleDataMiasma>() {

        @Nonnull
        @Override
        public ParticleDataMiasma deserialize(@Nonnull ParticleType<ParticleDataMiasma> type, @Nonnull StringReader reader) throws CommandSyntaxException {
            reader.expect(' ');
            double diameter = constrainDiameterToValidRange(reader.readDouble());

            final int MIN_COLOUR = 0;
            final int MAX_COLOUR = 255;
            reader.expect(' ');
            int red = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
            reader.expect(' ');
            int green = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
            reader.expect(' ');
            int blue = MathHelper.clamp(reader.readInt(), MIN_COLOUR, MAX_COLOUR);
            Color color = new Color(red, green, blue);

            return new ParticleDataMiasma(color, diameter);
        }

        @Override
        public ParticleDataMiasma read(@Nonnull ParticleType<ParticleDataMiasma> type, PacketBuffer buf) {

            final int MIN_COLOUR = 0;
            final int MAX_COLOUR = 255;
            int red = MathHelper.clamp(buf.readInt(), MIN_COLOUR, MAX_COLOUR);
            int green = MathHelper.clamp(buf.readInt(), MIN_COLOUR, MAX_COLOUR);
            int blue = MathHelper.clamp(buf.readInt(), MIN_COLOUR, MAX_COLOUR);
            Color color = new Color(red, green, blue);

            double diameter = constrainDiameterToValidRange(buf.readDouble());

            return new ParticleDataMiasma(color, diameter);
        }
    };

}
