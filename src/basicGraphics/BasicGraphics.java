package basicGraphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BasicGraphics {
	public static void main(String[] args) {
		/**
		 * 入力
		 */
		String srcFilePath = "test.jpg";
		File srcFile = new File(srcFilePath);
		BufferedImage srcImg = null;
		try {
			srcImg = ImageIO.read(srcFile);
		} catch (IOException e1) {
			// TODO 自動生成された catch ブロック
			e1.printStackTrace();
		}

		WritableRaster srcRas = srcImg.getRaster();
		DataBuffer srcBuf = srcRas.getDataBuffer();

		int w = srcImg.getWidth(), h = srcImg.getHeight();
		// 二次元配列に値を格納
		int src2d[][] = new int[h][w];
		for(int y = 0; y < h; y++){
			for(int x = 0; x < w; x++){
				src2d[y][x] = srcBuf.getElem(y*w+x);
			}
		}

		/**
		 * 処理
		 */		
		// 処理結果を格納する二次元配列
		int dst2d[][] = new int[h][w];
		for(int y = 0; y < h; y++){
			for(int x = 0; x < w; x++){
				dst2d[y][x] = (src2d[y][x] == 0) ? 255 : 0;
			}
		}

		/**
		 * 出力
		 */
		BufferedImage dstImg = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		WritableRaster dstRas = dstImg.getRaster();
		DataBuffer dstBuf = dstRas.getDataBuffer();

		// DataBuffer に値を格納
		for(int y = 0; y < h; y++){
			for(int x = 0; x < w; x++){
				dstBuf.setElem(y*w+x, dst2d[y][x]);
			}
		}

		String dstFilePath = "ret.jpg";
		File dstFile = new File(dstFilePath);
		try {
			ImageIO.write(dstImg, "jpg", dstFile);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
