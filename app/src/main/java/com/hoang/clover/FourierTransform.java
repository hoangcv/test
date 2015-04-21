package com.hoang.clover;

/**
 * Created by hoang on 21/04/2015.
 */

/**
 * Một biến đổi Fourier là một thuật toán mà biến đổi một tín hiệu trong thời gian
 * miền, ví dụ như một bộ đệm mẫu, thành một tín hiệu thuộc phạm vi tần số thường
 * gọi là quang phổ. Quang phổ không đại diện cho tần số cá nhân,
 * nhưng thực sự đại diện cho dải tần trung tâm trên tần số cụ thể.
 * Tần số Trung tâm của mỗi ban nhạc thường biểu thị dưới dạng một phần nhỏ của các
 * Lấy mẫu tỷ lệ tín hiệu tên miền thời gian và là tương đương với chỉ số của các
 * tần số ban nhạc chia cho tổng số của ban nhạc. Tổng số
 * tần số ban nhạc là thường tương đương với chiều dài của tín hiệu tên miền thời gian, nhưng
 * truy cập chỉ cung cấp cho dải tần số với chỉ số ít hơn một nửa các
 * chiều dài, bởi vì họ tương ứng với các tần số dưới đây các < một
 * href = "http://en.wikipedia.org/wiki/Nyquist_frequency" > tần số Nyquist</a>.
 * Trong nói cách khác, đưa ra một tín hiệu của chiều dài <code>N</code>, sẽ có
 Dải tần số <code>N/2</code> trong quang phổ.
 * <p>
 * Là một ví dụ, nếu bạn xây dựng một FourierTransform với một
 <code>timeSize</code> 1024 và và một <code>sampleRate</code> của 44100
 * Hz, sau đó quang phổ sẽ chứa các giá trị đối với các tần số dưới đây 22010 Hz,
 * đó là tần số Nyquist (một nửa các tỷ lệ mẫu). Nếu bạn yêu cầu cho các
 * giá trị của ban nhạc số 5, điều này sẽ tương ứng với một băng tần trung tâm trên
 * <code>5/1024 * 44100 = 0.0048828125 * 44100 = 215 Hz</code>. Chiều rộng của
 * đó băng tần là tương đương với <code>2/1024</code>, thể hiện như là một
 * phần của băng thông tất cả của quang phổ. Tất cả băng thông của các
 * phổ là tương đương với tần số Nyquist, trong trường hợp này là 22050, vì vậy
 * băng thông là tương đương với khoảng 50 Hz. Nó không phải là cần thiết để bạn có thể
 * Hãy nhớ tất cả các mối quan hệ, mặc dù nó là tốt để được nhận thức của họ.
 * Chức năng <code>getFreq()</code> cho phép bạn truy vấn phổ với một
 * tần số trong Hz và chức năng <code>getBandWidth()</code> sẽ trở lại
 * băng thông ở Hz của mỗi băng tần trong quang phổ.
 * <p>
 <b>Sử dụng</b>
 * <p>
 * Một cách sử dụng điển hình của một FourierTransform là để phân tích một tín hiệu để các
 * tần số quang phổ có thể được biểu diễn bằng một số cách, thường với đứng
 * đường. Bạn có thể làm điều này trong xử lý với đoạn mã sau, nơi
 <code>âm thanh</code> là một AudioSource và <code>fft</code> là một FFT (một
 * Các lớp học có nguồn gốc của FourierTransform).
 *
 * <pre>
 * fft.forward(audio.left);
 * cho (int tôi = 0; tôi &lt; fft.specSize(); i ++)
 * {
 * / / rút ra những dòng cho băng tần i, mở rộng quy mô nó bởi 4 để chúng tôi có thể nhìn thấy nó một chút tốt hơn
 * line(i, height, i, height-fft.getBand(i) * 4);
 * }
 * </pre>
 *
 <b>Cửa sổ</b>
 * <p>
 * Cửa sổ là quá trình hình thành các mẫu âm thanh trước khi chuyển đổi chúng
 * để miền tần số. Biến đổi Fourier giả định các bộ đệm mẫu là là một
 * lặp đi lặp lại các tín hiệu, nếu một bộ đệm mẫu không phải là thực sự định kỳ trong các đo
 * khoảng sắc nét discontinuities có thể phát sinh mà có thể giới thiệu quang phổ rò rỉ.
 * Quang phổ rò rỉ là speading tín hiệu năng lượng trên nhiều FFT thùng. Điều này
 * "lây lan" có thể rút ra khỏi dải hẹp tín hiệu và cản trở phát hiện.
 * </p>
 * <p>
 * Một <a href="http://en.wikipedia.org/wiki/Window_function"> chức năng cửa sổ</a>
 * nỗ lực để giảm rò rỉ quang phổ của sập đệm đo mẫu
 * lúc của nó điểm để loại bỏ discontinuities. Nếu bạn gọi <code>window()</code>
 * chức năng với một WindowFunction thích hợp, chẳng hạn như <code>HammingWindow()</code>,
 * Các bộ đệm mẫu được truyền cho các đối tượng để phân tích sẽ được định hình bởi hiện tại
 * cửa sổ trước khi được chuyển đổi. Kết quả của việc sử dụng một cửa sổ là để giảm
 * sự rò rỉ trong quang phổ hơi.
 * <p>
 <b>Trung bình</b>
 * <p>
 * FourierTransform cũng có chức năng cho phép bạn để yêu cầu sáng tạo của
 * một quang phổ trung bình. Một quang phổ trung bình là chỉ đơn giản là một quang phổ với ít
 * Ban nhạc hơn đầy đủ mà mỗi ban nhạc trung bình là mức trung bình của các
 * amplitudes của một số số tiếp giáp tần số ban nhạc trong đầy đủ.
 * <p>
 <code>linAverages()</code> cho phép bạn chỉ định số trung bình
 * rằng bạn muốn và sẽ nhóm dải tần số vào nhóm của số lượng tương đương. Vì vậy
 * Nếu bạn có một phổ với 512 dải tần số và bạn yêu cầu cho 64 trung bình,
 * mỗi là sẽ khoảng 8 ban nhạc của quang phổ đầy đủ.
 * <p>
 <code>logAverages()</code> sẽ nhóm dải tần số theo octave và cho phép
 * bạn phải xác định kích thước của octave nhỏ nhất để sử dụng (trong Hz) và cũng như thế nào
 * nhiều ban nhạc để tách mỗi octave vào. Vì vậy, bạn có thể yêu cầu cho nhỏ nhất
 * octave là 60 Hz và mỗi octave chia hai ban nhạc. Kết quả là
 * rằng băng thông trung bình mỗi là khác nhau. Một tần số là một octave
 * trên khác khi nó là tần số là hai lần mà tần số thấp hơn. Vì vậy,
 * 120 Hz là một octave trên 60 Hz, 240 Hz là một octave trên 120 Hz, và như vậy.
 * Khi đặc được tách ra, họ được phân chia dựa trên Hz, s
*/
public abstract class FourierTransform {

    protected static final int LINAVG = 1;
    protected static final int LOGAVG = 2;
    protected static final int NOAVG = 3;

    protected static final float TWO_PI = (float) (2 * Math.PI);
    protected int timeSize;
    protected int sampleRate;
    protected float bandWidth;
    protected float[] real;
    protected float[] imag;
    protected float[] spectrum;
    protected float[] averages;
    protected int whichAverage;
    protected int octaves;
    protected int avgPerOctave;

    /**
     * Construct a FourierTransform that will analyze sample buffers that are
     * <code>ts</code> samples long and contain samples with a <code>sr</code>
     * sample rate.
     *
     * @param ts
     *          the length of the buffers that will be analyzed
     * @param sr
     *          the sample rate of the samples that will be analyzed
     */
    FourierTransform (int ts, float sr){
        timeSize = ts;
        sampleRate = (int) sr;
        bandWidth = (2f / timeSize) * ((float) sampleRate /2f );
        noAverages();
        allocateArrays();
    }

    // allocating real, imag, and spectrum are the responsibility of derived
    // classes
    // because the size of the arrays will depend on the implementation being used
    // this enforces that responsibility
    protected abstract void allocateArrays();

    protected void setComplex(float[] r, float[] i){
        if(real.length != r.length && imag.length != i.length){

        } else {
            System.arraycopy(r, 0, real, 0, r.length);
            System.arraycopy(i, 0, real, 0, i.length);
        }
    }

    // fill the spectrum array with the amps of the data in real and imag
    // used so that this class can handle creating the average array
    // and also do spectrum shaping if necessary
    protected void fillSpectrum(){
        for (int i = 0; i < spectrum.length; i++){
            spectrum[i] = (float) Math.sqrt(real[i] * real[i] * imag[i] *imag[i]);
        }
        if (whichAverage == LINAVG){
            int avgWidth = (int) spectrum.length / averages.length;
            for (int i = 0; i < averages.length; i++){
                float avg = 0;
                int j;
                for (j = 0; j < avgWidth; j++){
                    int offset = j + i *avgWidth;
                    if (offset < spectrum.length){
                        avg += spectrum[offset];
                    } else {
                        break;
                    }
                }
                avg /= j +1;
                averages[i] = avg;
            }
        } else if (whichAverage == LOGAVG){
            for (int i = 0; i <octaves; i++){
                float lowFreq, hiFreq, freqStep;
                if (i == 0){
                    lowFreq = 0;
                } else {
                    lowFreq = (sampleRate / 2) / (float) Math.pow(2, octaves - i);
                }
                hiFreq = (sampleRate /2) / (float) Math.pow(2, octaves - i - 1);
                freqStep = (hiFreq - lowFreq) /avgPerOctave;
                float f = lowFreq;
                for (int j = 0; j < avgPerOctave; j++){
                    int offset = j + i * avgPerOctave;
                    averages[offset] = calcAvg(f, f + freqStep);
                    f += freqStep;
                }
            }
        }
    }

    /**
     * Sets the object to not compute averages.
     *
     */
    public void noAverages(){
        averages = new float[0];
        whichAverage = NOAVG;
    }

    /**
     * Sets the number of averages used when computing the spectrum and spaces the
     * averages in a linear manner. In other words, each average band will be
     * <code>specSize() / numAvg</code> bands wide.
     *
     * @param numAvg
     *          how many averages to compute
     */
    public void linAverages (int numAvg){
        if (numAvg >spectrum.length / 2){
            return;
        } else {
            averages = new float[numAvg];
        }
        whichAverage = LINAVG;
    }

    /**
     * Sets the number of averages used when computing the spectrum based on the
     * minimum bandwidth for an octave and the number of bands per octave. For
     * example, with audio that has a sample rate of 44100 Hz,
     * <code>logAverages(11, 1)</code> will result in 12 averages, each
     * corresponding to an octave, the first spanning 0 to 11 Hz. To ensure that
     * each octave band is a full octave, the number of octaves is computed by
     * dividing the Nyquist frequency by two, and then the result of that by two,
     * and so on. This means that the actual bandwidth of the lowest octave may
     * not be exactly the value specified.
     *
     * @param minBandwidth
     *          the minimum bandwidth used for an octave
     * @param bandsPerOctave
     *          how many bands to split each octave into
     */
    public void logAverages (int minBandwidth, int bandsPerOctave){
        float nyq = (float) sampleRate / 2f;
        octaves = 1;
        while ((nyq /= 2) > minBandwidth){
            octaves++;
        }
        avgPerOctave = bandsPerOctave;
        averages = new float[octaves * bandsPerOctave];
        whichAverage = LOGAVG;
    }

    /**
     * Returns the length of the time domain signal expected by this transform.
     *
     * @return the length of the time domain signal expected by this transform
     */
    public int timeSize(){
        return timeSize;
    }

    /**
     * Returns the size of the spectrum created by this transform. In other words,
     * the number of frequency bands produced by this transform. This is typically
     * equal to <code>timeSize()/2 + 1</code>, see above for an explanation.
     *
     * @return the size of the spectrum
     */
    /**
     * Trả về size của quang phổ được tạo ra bởi biến đổi này
     * Nói cách khác, số lượng các dải tần số được sản xuất bởi biến đổi này.
     * Điều này là thường tương đương với <code>() timeSize / 2 + 1</code>,
     * xem ở trên để giải thích.
     * @return
     */
    public int specSize(){
        return spectrum.length;
    }

    public float getBand(int i){
        if (i < 0){
            i = 0;
        }
        if (i > spectrum.length - 1){
            i = spectrum.length - 1;
        }
        return spectrum[i];
    }

    /**
     * Returns the width of each frequency band in the spectrum (in Hz). It should
     * be noted that the bandwidth of the first and last frequency bands is half
     * as large as the value returned by this function.
     *
     * @return the width of each frequency band in Hz.
     */
    /**
     * Trả về chiều rộng của băng tần trong quang phổ. Cần lưu ý
     * rằng băng thông của dải tần số đầu tiên
     * và cuối cùng là một nửa lớn như giá trị trả lại bằng chức năng này.
     * @return trả về chiều rộng của mỗi băng tần (Hz)
     */
    public float getBandWidth(){
        return bandWidth;
    }

    /**
     * Returns the bandwidth of the requested average band. Using this information
     * and the return value of getAverageCenterFrequency you can determine the
     * lower and upper frequency of any average band.
     *
     * @see #getAverageCenterFrequency(int)
     * @related getAverageCenterFrequency ( )
     */
    /**
     * Trả về dải băng thông của băng tần trung bình yêu cầu.
     * Sử dụng thông tin này và giá trị trả về của getAverageCenterFrequency
     * tần số bạn có thể xác định các tần số thấp hơn và trên của bất kỳ băng tần
     * trung bình.
     * @param averageIndex
     * @return
     */
    public float getAverageBandWidth(int averageIndex){
        if (whichAverage == LINAVG){
            int avgWidth = (int) spectrum.length / averages.length;
            return avgWidth *getBandWidth();
        } else if (whichAverage == LOGAVG){
            int octave = averageIndex / avgPerOctave;
            float lowFreq, hiFreq, freqStep;
            if (octave == 0){
                lowFreq = 0;
            } else {
                lowFreq = (sampleRate / 2) / (float) Math.pow(2, octaves - octave);
            }
            hiFreq = (sampleRate / 2) / (float) Math.pow(2, octaves - octave - 1);
            freqStep = (hiFreq - lowFreq) / avgPerOctave;
            return freqStep;
        }
        return 0;
    }

    /**
     * Sets the amplitude of the <code>i<sup>th</sup></code> frequency band to
     * <code>a</code>. You can use this to shape the spectrum before using
     * <code>inverse()</code>.
     *
     * @param i
     *          the frequency band to modify
     * @param a
     *          the new amplitude
     */
    /**
     * Thiết lập biên độ của băng tần.
     * Bạn có thể sử dụng để hình dạng quang phổ trước khi sử dụng inverse()
     * @param i băng tần để sửa đổi
     * @param a biên độ mới
     */
    public abstract void setBand(int i, float a);

    /**
     * Scales the amplitude of the <code>i<sup>th</sup></code> frequency band
     * by <code>s</code>. You can use this to shape the spectrum before using
     * <code>inverse()</code>.
     *
     * @param i
     *          the frequency band to modify
     * @param s
     *          the scaling factor
     */

    /**
     * Quy mô biên độ của các băng tần
     * @param i băng tần để sửa đổi
     * @param s Hệ số tỉ lệ
     */
    public abstract void scaleBand(int i, float s);

    /**
     * Returns the index of the frequency band that contains the requested
     * frequency.
     *
     * @param freq
     *          the frequency you want the index for (in Hz)
     * @return the index of the frequency band that contains freq
     */
    /**
     *  Trả về chỉ số của băng tần chứa có chứa yêu cầu
     * @param freq
     *          tần số mà bạn muốn chỉ số
     * @return các chỉ số của các băng tần có chứa freq
     */
    public int freqToIndex(float freq){
        // special case: freq is lower than the bandwidth of spectrum[0]
        if(freq < getBandWidth() / 2){
            return 0;
        }
        // special case: freq is within the bandwidth of spectrum[spectrum.length - 1]
        if (freq > sampleRate /2 - getBandWidth() / 2){
            return spectrum.length - 1;
        }
        // all other cases
        float fraction = freq / (float) sampleRate;
        int i = Math.round(timeSize * fraction);
        return i;
    }

    /**
     * Returns the middle frequency of the i<sup>th</sup> band.
     * @param i
     *        the index of the band you want to middle frequency of
     */
    /**
     *
     * @param i
     * @return Tần số giữa các băng tần
     */
    public float indexToFreq(int i){
        float bw = getBandWidth();
        //special case: the width of the first bin is half that of the others.
        //               so the center frequency is a quarter of the way.
        if(i == 0){
            return bw * 0.25f;
        }
        // special case: the width of the last bin is half that of the others.
        if (i == spectrum.length -1){
            float lastBinBeginFreq = (sampleRate / 2) - (bw / 2);
            float binHalfWidth = bw * 0.25f;
            return lastBinBeginFreq + binHalfWidth;
        }
        // the center frequency of the ith band is simply i*bw
        // because the first band is half the width of all others.
        // treating it as if it wasn't offsets us to the middle
        // of the band.
        return i * bw;
    }

    /**
     * Returns the center frequency of the i<sup>th</sup> average band.
     *
     * @param i
     *     which average band you want the center frequency of.
     */
    /**
     *
     * @param i
     * @return
     */
    public float getAverrageCenterFrequency(int i){
        if (whichAverage == LINAVG){
            // an average represents a certain number of bands in the spectrum
            // bình quân đại diện cho một số lượng nhất định các dải trong quang phổ
            int avgWidth = (int) spectrum.length / averages.length;
            // the "center" bin of the average, this is fudgy.
            int centerBinIndex = i * avgWidth + avgWidth / 2;
            return indexToFreq(centerBinIndex);
        } else if (whichAverage == LOGAVG){
            // which "octave" is this index in?
            int octave = i / avgPerOctave;
            // which band within that octave is this?
            int offset = i % avgPerOctave;
            float lowFreq, hiFreq, freqStep;
            // figure out the low frequency for this octave
            //Tìm ra tần số thấp cho octave này
            if(octave == 0){
                lowFreq = 0;
            } else {
                lowFreq = (sampleRate / 2) /(float) Math.pow(2, octaves - octave);
            }
            // and the high frequency for this octave
            // và tần số cao cho octave này
            hiFreq = (sampleRate / 2) / (float) Math.pow(2, octaves - octave - 1);
            // each average band within the octave will be this big
            // mỗi băng tần trung bình trong octave sẽ lớn
            freqStep = (hiFreq - lowFreq) / avgPerOctave;
            // figure out the low frequency of the band we care about
            // Tìm ra tần số thấp của các băng tần chúng tôi quan tâm
            float f = lowFreq + offset * freqStep;
            // the center of the band will be the low plus half the width
            // trung tâm của băng tần sẽ là tần số thấp cộng với nửa chiều rộng
            return f + freqStep / 2;
        }
        return 0;
    }

    /**
     * Gets the amplitude of the requested frequency in the spectrum.
     *
     * @param freq
     *          the frequency in Hz
     * @return the amplitude of the frequency in the spectrum
     */
    /**
     * Lấy biên độ tần số được yêu cầu trong quang phổ
     * @param freq
     * @return biên độ của tần số trong quang phổ
     */
    public float getFreq (float freq){
        return getBand(freqToIndex(freq));
    }

    /**
     * Sets the amplitude of the requested frequency in the spectrum to
     * <code>a</code>.
     *
     * @param freq
     *          the frequency in Hz
     * @param a
     *          the new amplitude
     */
    /**
     * Thiết lập các biên độ của tần số yêu cầu trong quang phổ
     * @param freq
     * @param a biên độ mới
     */
    public void setFreq(float freq, float a){
        setBand(freqToIndex(freq), a);
    }

    /**
     * Scales the amplitude of the requested frequency by <code>s</code>.
     *
     * @param freq
     *          the frequency in Hz
     * @param s
     *          the scaling factor
     */
    /**
     * Quy mô biên độ tần số được yêu cầu bởi <code>s</code>
     * @param freq
     *          tần số trong HZ
     * @param s
     *          hệ số tỉ lệ
     */
    public void scaleFreq(float freq, float s){
        scaleBand(freqToIndex(freq), s);
    }

    /**
     * Returns the number of averages currently being calculated.
     *
     * @return the length of the averages array
     */
    /**
     * Trả về số lượng averages đang được tính toán
     * @return chiều dài của mảng averages
     */
    public int avgSize(){
        return averages.length;
    }

    /**
     * Gets the value of the <code>i<sup>th</sup></code> average.
     *
     * @param i
     *          the average you want the value of
     * @return the value of the requested average band
     */

    /**
     * Lấy giá trị averages[i]
     * @param i
     * @return giá trị của băng tần yêu cầu
     */
    public float getAvg(int i){
        float ret;
        if (averages.length > 0 ){
            ret = averages[i];
        } else {
            ret = 0;
        }
        return ret;
    }

    /**
     * Calculate the average amplitude of the frequency band bounded by
     * <code>lowFreq</code> and <code>hiFreq</code>, inclusive.
     *
     * @param lowFreq
     *          the lower bound of the band
     * @param hiFreq
     *          the upper bound of the band
     * @return the average of all spectrum values within the bounds
     */

    /**
     * Tính biên độ trung bình của các băng tần số giới hạn bởi
     * lowFreq và hiFreq
     * @param lowFreq
     *          giới hạn dưới của dải
     * @param hiFreq
     *          giới hạn trên của dải
     * @return mức trung bình của tất cả các giá trị phổ trong các giới hạn
     */
    public float calcAvg(float lowFreq, float hiFreq){
        int lowBound = freqToIndex(lowFreq);
        int hiBound = freqToIndex(hiFreq);
        float avg = 0;
        for (int i = lowBound; i <= hiBound; i++){
            avg += spectrum[i];
        }
        avg /= (hiBound - lowBound + 1);
        return avg;
    }

    /**
     * Get the Real part of the Complex representation of the spectrum.
     * Lấy phần thực của phổ tần
     */
    public float[] getSpectrumReal(){
        return real;
    }

    /**
     * Get the Imaginary part of the Complex representation of the spectrum.
     * Lấy phần ảo của phổ tần
     */

    public float[] getSpectrumImaginary(){
        return imag;
    }

    /**
     * Performs a forward transform on <code>buffer</code>.
     *
     * @param buffer
     *          the buffer to analyze
     */

    /**
     * Thực hiện một chuyển tiếp chuyển đổi trên buffer
     * @param buffer
     *          buffer để phân tích
     */

    public abstract void forward(float[] buffer);

    /**
     * Performs a forward transform on values in <code>buffer</code>.
     *
     * @param buffer
     *          the buffer of samples
     * @param startAt
     *          the index to start at in the buffer. there must be at least timeSize samples
     *          between the starting index and the end of the buffer. If there aren't, an
     *          error will be issued and the operation will not be performed.
     *
     */

    /**
     * Thực hiện một chuyển tiếp chuyển đổi các giá trị trong bufer
     * @param buffer
     *          buffer của các mẫu
     * @param startAt
     *          chỉ số để bắt đầu trông buffer, cần phải có ít nhất timeSize
     *          mẫu giữa các chỉ số bắt đầu và kết thúc của bộ đệm. Nếu không có,
     *          một lỗi sẽ được phát sinh và các hoạt động sẽ không được thực hiện
     */
    public void forward (float[] buffer, int startAt){
        if (buffer.length - startAt < timeSize){
            return;
        }
        float[] section = new float[timeSize];
        System.arraycopy(buffer, startAt, section, 0, section.length);
        forward(section);
    }

    /**
     * Performs an inverse transform of the frequency spectrum and places the
     * result in <code>buffer</code>.
     *
     * @param buffer
     *          the buffer to place the result of the inverse transform in
     */

    /**
     * Thực hiện một nghịch đảo của phổ tần số và đặt kết quả vào buffer
     * @param buffer
     */
    public abstract void inverse(float[] buffer);

    /**
     * Performs an inverse transform of the frequency spectrum represented by
     * freqReal and freqImag and places the result in buffer.
     *
     * @param freqReal
     *          the real part of the frequency spectrum
     * @param freqImag
     *          the imaginary part the frequency spectrum
     * @param buffer
     *          the buffer to place the inverse transform in
     */

    /**
     * Thực hiện một nghịch đảo của phổ tần được đại diện bởi freqReal, freqImag
     * và đặt kết quả vào buffer
     * @param freqReal
     *          phần thực của tần số
     * @param freqImag
     *          phần ảo của tần số
     * @param buffer
     *
     */
    public void inverse(float[] freqReal, float[] freqImag, float[] buffer){
        setComplex(freqReal, freqImag);
        inverse(buffer);
    }
}
