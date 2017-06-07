#include <stdio.h>
#include <math.h>
void DFT(int n, double *real, double *imag)
{
    double tmpReal[n], tmpImag[n];
    
    for (int i = 0; i < n; i++) {
        
        tmpReal[i] = 0.0;
        tmpImag[i] = 0.0;
        
        double d = 2.0 * M_PI * i / n;
        
        for (int j = 0; j < n; j++) {
            
            double phase = d * j;
            
            tmpReal[i] += real[j] * cos(phase);
            tmpImag[i] -= real[j] * sin(phase);
        }
    }
    
    for (int i = 0; i < n; i++) {
        real[i] = tmpReal[i];
        imag[i] = tmpImag[i];
    }
}
int main (int argc, const char * argv[]) {
    
    int n = 16;
    double real[n], imag[n];
    
    double d = 2.0 * M_PI / n;
    
    for (int i = 0; i < n; i++) {
        real[i] = sin(1.0 * i * d); //1Hzのサイン波
        real[i] += sin(3.0 * i * d + M_PI_4); //3Hzのサイン波（1/4πずらし）
        real[i] += sin(5.0 * i * d + M_PI_2); //5Hzのサイン波（1/2πずらし）
        imag[i] = 0.0;
    }
    
    //フーリエ変換
    DFT(n, real, imag);
    
    for (int i = 0; i < n; i++) {
        printf("%dHz %f\n", i, sqrt(real[i] * real[i] + imag[i] * imag[i]));
    }
    
    return 0;
}