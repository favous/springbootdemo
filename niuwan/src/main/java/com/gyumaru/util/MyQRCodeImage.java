package com.gyumaru.util;

import java.awt.image.BufferedImage;

import jp.sourceforge.qrcode.data.QRCodeImage;

public class MyQRCodeImage implements QRCodeImage{

	 BufferedImage bufferedImage;

    public MyQRCodeImage(BufferedImage bufferedImage){
        this.bufferedImage=bufferedImage;
    }
	
	public int getHeight() {
		return bufferedImage.getHeight();
	}

	public int getPixel(int i, int j) {
		return bufferedImage.getRGB(i, j);
	}

	public int getWidth() {
		return bufferedImage.getWidth();
	}

}
