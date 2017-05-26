package highPathLowPath;

import static img.ImageUtility.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class HighPath {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		File f = new File("test.jpg");
		BufferedImage read = null;
		try {
			read = ImageIO.read(f);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		int w = read.getWidth(),h=read.getHeight();
		BufferedImage write =
				new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

		for(int y=0;y<h;y++){
			for(int x=0;x<w;x++){
				int c = read.getRGB(x, y);
				int r = 255-r(c);
				int g = 255-g(c);
				int b = 255-b(c);
				int rgb = rgb(r,g,b);
				write.setRGB(x,y,rgb);
			}
		}

		File f2 = new File("ret.jpg");
		try {
			ImageIO.write(write, "jpg", f2);
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}



}
