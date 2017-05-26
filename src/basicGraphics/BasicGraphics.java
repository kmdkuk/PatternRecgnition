package basicGraphics;

import java.awt.image.BufferedImage;
import java.awt.image.DataBuffer;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BasicGraphics {
	void main() {
		// 入力
		BufferedImage srcImg = null;
		try {
			srcImg = ImageIO.read(new File("test.jpg"));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		DataBuffer srcBuf = srcImg.getRaster().getDataBuffer();

		int w = srcImg.getWidth(), h = srcImg.getHeight();

		// 出力バッファ
		BufferedImage dstImg = new BufferedImage(w, h, BufferedImage.TYPE_BYTE_GRAY);
		DataBuffer dstBuf = dstImg.getRaster().getDataBuffer();

		// 処理
		for(int i = 0; i < dstBuf.getSize(); i++){
			dstBuf.setElem(i, (srcBuf.getElem(i) == 0) ? 255 : 0);
		}

		// 出力
		try {
			ImageIO.write(dstImg, "jpg", new File("ret.jpg"));
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
