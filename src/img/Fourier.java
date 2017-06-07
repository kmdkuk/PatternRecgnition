package img;

public class Fourier {
	void dft(double real[][], boolean inv) {		
		int wid = real.length;
		int hgt = real[0].length;
		double image[][] = new double[wid][hgt];

		// このルーチンは順変換（画像→フーリエ）、逆変換両方に使い回せる
		// realには実数（順変換では元画像の値）、imageには虚数（はじめは0）が入っている
		// invは順変換ではfalse、逆変換ではtrue
		// 変換後の値はreal、imageを書き換えて保存される
		double M2 = (inv) ? (2*Math.PI) : (-2*Math.PI);
		double wnorm = (inv) ? (1.0/wid) : 1.0;
		double hnorm = (inv) ? (1.0/hgt) : 1.0;
		double rw = 1.0/wid;
		double rh = 1.0/hgt;

		// X軸方向のみDFT
		double tre[][] = new double[wid][hgt];
		double tim[][] = new double[wid][hgt];
		{
			// テーブル生成
			// Y軸毎に使い回せる部分は予め作っておく
			double wrtable[][] = new double[wid][wid];
			double witable[][] = new double[wid][wid];

			for (int i = 0; i < wid; ++i) {
				for (int j = 0; j < wid; ++j) {
					wrtable[i][j] = Math.cos(M2*i*j*rw);
					witable[i][j] = Math.sin(M2*i*j*rw);
				}
			}

			// 変換
			// y軸毎に変換する
			for (int y = 0; y < hgt; ++y) {

				// 一次元DFT
				for (int x = 0; x < wid; ++x) {
					tre[x][y] = tim[x][y] = 0.0;
					for (int xx = 0; xx < wid; ++xx) {
						tre[x][y] += real[xx][y]*wrtable[x][xx] - image[xx][y]*witable[x][xx];
						tim[x][y] += real[xx][y]*witable[x][xx] + image[xx][y]*wrtable[x][xx];
					}
					// 逆変換の場合には1/widをかける
					tre[x][y] *= wnorm;
					tim[x][y] *= wnorm;
				}
			}
		}

		// Y軸方向のみDFT
		// X軸と同じ要領で行う
		{
			// テーブル生成
			double hrtable[][] = new double[hgt][hgt];
			double hitable[][] = new double[hgt][hgt];

			for (int i = 0; i < hgt; ++i) {
				for (int j = 0; j < hgt; ++j) {
					hrtable[i][j] = Math.cos(M2*i*j*rh);
					hitable[i][j] = Math.sin(M2*i*j*rh);
				}
			}

			// 変換
			for (int x = 0; x < wid; ++x) {
				for (int y = 0; y < hgt; ++y) {
					real[x][y] = image[x][y] = 0.0;
					for (int yy = 0; yy < hgt; ++yy) {
						real[x][y] += tre[x][yy]*hrtable[y][yy] - tim[x][yy]*hitable[y][yy];
						image[x][y] += tre[x][yy]*hitable[y][yy] + tim[x][yy]*hrtable[y][yy];
					}
					real[x][y] *= hnorm;
					image[x][y] *= hnorm;
				}
			}
		}
	}
}
