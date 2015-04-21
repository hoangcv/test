package com.hoang.clover;

/**
 * Created by hoang on 21/04/2015.
 */
public class FFT extends FourierTransform {
    /**
     * Construct a FourierTransform that will analyze sample buffers that are
     * <code>ts</code> samples long and contain samples with a <code>sr</code>
     * sample rate.
     *
     * @param timeSize the length of the buffers that will be analyzed
     * @param samplerate
     */
    FFT(int timeSize, float samplerate) {
        super(timeSize, samplerate);
        if ((timeSize & (timeSize - 1)) != 0){
            throw new IllegalArgumentException("RRT: timeSize must be a power of two.");
        }
        buildReverseTable();
        buildTrigTables();
    }

    @Override
    protected void allocateArrays() {
        spectrum = new float[timeSize / 2 +1];
        real = new float[timeSize];
        imag = new float[timeSize];
    }

    @Override
    public void scaleBand(int i, float s) {
        if (s < 0){
            return;
        }
        real[i] *= s;
        imag[i] *= s;
        spectrum[i] *= s;
        if (i != 0 && i != (timeSize / 2)){
            real[timeSize - 1] = real[i];
            imag[timeSize - 1] = imag[i];
        }
    }

    @Override
    public void setBand(int i, float a) {
        if (a < 0){
            return;
        } if (real[i] == 0 && imag[i] == 0){
            real[i] = a;
            spectrum[i] = a;
        } else {
            real[i] /= spectrum[i];
            imag[i] /= spectrum[i];
            spectrum[i] = a;
            real[i] *= spectrum[i];
            imag[i] *= spectrum[i];
        }
        if (i != 0 && i != timeSize /2){
            real[timeSize -1] = real[i];
            imag[timeSize -1] = -imag[i];
        }
    }

    private void fft(){
        for (int halfSize = 1; halfSize <real.length; halfSize++){

        }
    }

    @Override
    public void forward(float[] buffer) {

    }

    @Override
    public void inverse(float[] buffer) {

    }

    private void buildReverseTable() {
    }

    private void buildTrigTables() {
    }
}
