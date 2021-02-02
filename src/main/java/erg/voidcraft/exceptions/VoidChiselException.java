package erg.voidcraft.exceptions;

import net.minecraft.util.math.BlockPos;

public class VoidChiselException extends Exception {

    private int d;
    private int i;
    private int n;

    private BlockPos bottomLeft;
    private BlockPos index;

    public VoidChiselException(int d, int i, int n, BlockPos bottomLeft, BlockPos index) {
        super();
        this.d = d;
        this.i = i;
        this.n = n;
        this.bottomLeft = bottomLeft;
        this.index = index;
    }

    @Override
    public String getMessage() {
        return "Void Chisel fucked something up.\n" +
                "d: " + this.d + " i: " + this.i + " n: " + this.n + "\n" +
                "BottomLeft:\n\tX: " + bottomLeft.getX() + "\n\tY: " + bottomLeft.getY() + "\n\t Z: " + bottomLeft.getZ() + "\n" +
                "index:\n\tX: " + index.getX() + "\n\tY: " + index.getY() + "\n\t Z: " + index.getZ();
     }
}
