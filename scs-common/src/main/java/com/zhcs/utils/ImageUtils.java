package com.zhcs.utils;

import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class ImageUtils {

	//*************************************************************************
	/** 
	* 【裁剪】按大小居中裁减
	* @param data
	* @param w
	* @param h
	* @return
	* @throws IOException  
	*/
	//*************************************************************************
	public static byte[] cutCenterImage(byte[] data, int w, int h) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Iterator<ImageReader> iterator = ImageIO.getImageReadersByFormatName("jpg");
		ImageReader reader = iterator.next();
		ImageInputStream iis = ImageIO.createImageInputStream(new ByteArrayInputStream(data));
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		int imageIndex = 0;
		Rectangle rect = new Rectangle((reader.getWidth(imageIndex) - w) / 2, (reader.getHeight(imageIndex) - h) / 2, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, "jpg", baos);
		return baos.toByteArray();
	}

	// /*
	// * 图片裁剪二分之一
	// */
	// public static void cutHalfImage(String src, String dest) throws
	// IOException {
	// Iterator<ImageReader> iterator =
	// ImageIO.getImageReadersByFormatName("jpg");
	// ImageReader reader = iterator.next();
	// InputStream in = new FileInputStream(src);
	// ImageInputStream iis = ImageIO.createImageInputStream(in);
	// reader.setInput(iis, true);
	// ImageReadParam param = reader.getDefaultReadParam();
	// int imageIndex = 0;
	// int width = reader.getWidth(imageIndex) / 2;
	// int height = reader.getHeight(imageIndex) / 2;
	// Rectangle rect = new Rectangle(width / 2, height / 2, width, height);
	// param.setSourceRegion(rect);
	// BufferedImage bi = reader.read(0, param);
	// ImageIO.write(bi, "jpg", new File(dest));
	// }
	//
	// /*
	// * 图片裁剪通用接口
	// */
	// public static void cutImage(String src, String dest, int x, int y, int w,
	// int h) throws IOException {
	// Iterator<ImageReader> iterator =
	// ImageIO.getImageReadersByFormatName("jpg");
	// ImageReader reader = iterator.next();
	// InputStream in = new FileInputStream(src);
	// ImageInputStream iis = ImageIO.createImageInputStream(in);
	// reader.setInput(iis, true);
	// ImageReadParam param = reader.getDefaultReadParam();
	// Rectangle rect = new Rectangle(x, y, w, h);
	// param.setSourceRegion(rect);
	// BufferedImage bi = reader.read(0, param);
	// ImageIO.write(bi, "jpg", new File(dest));
	// }
	
	//*************************************************************************
	/** 
	* 【裁剪】指定宽高缩放
	* @param data
	* @param w
	* @param h
	* @return
	* @throws IOException  
	*/
	//*************************************************************************
	public static byte[] zoomImage(byte[] data, int w, int h) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		double wr = 0, hr = 0;
		BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(data));
		if (w <= 0) {
			w = bufImg.getWidth();
		}
		if (h <= 0) {
			h = bufImg.getHeight();
		}
		bufImg.getScaledInstance(w, h, BufferedImage.SCALE_SMOOTH);
		wr = w * 1.0 / bufImg.getWidth();

		hr = h * 1.0 / bufImg.getHeight();
		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(wr, hr), null);
		bufImg = ato.filter(bufImg, null);

		ImageIO.write(bufImg, "jpg", baos);
		return baos.toByteArray();
	}

	//*************************************************************************
	/** 
	* 【裁剪】等比例缩放图片
	* @param data
	* @param w
	* @param h
	* @return
	* @throws IOException  
	*/
	//*************************************************************************
	public static byte[] scaleImage(byte[] data, int w, int h) throws Exception {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		double wr = 0, hr = 0, r = 0;
		BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(data));
		if (w <= 0) {
			w = bufImg.getWidth();
		}
		if (h <= 0) {
			h = bufImg.getHeight();
		}
		bufImg.getScaledInstance(w, h, BufferedImage.SCALE_SMOOTH);
		wr = w * 1.0 / bufImg.getWidth();
		hr = h * 1.0 / bufImg.getHeight();
		r = wr <= hr ? wr : hr;
		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(r, r), null);
		bufImg = ato.filter(bufImg, null);

		ImageIO.write(bufImg, "jpg", baos);
		return baos.toByteArray();
	}

	//*************************************************************************
	/** 
	* 【裁剪】按大小缩放后居中裁减
	* @param data
	* @param w
	* @param h
	* @return
	* @throws IOException  
	*/
	//*************************************************************************
	public static byte[] scaleCenterImage(byte[] data, int w, int h) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BufferedImage bufImg = ImageIO.read(new ByteArrayInputStream(data));
		double rw = w * 1.0 / bufImg.getWidth();
		double rh = h * 1.0 / bufImg.getHeight();
		double r = rw > rh ? rw : rh;
		AffineTransformOp ato = new AffineTransformOp(AffineTransform.getScaleInstance(r, r), null);
		bufImg = ato.filter(bufImg, null);
		ImageIO.write(bufImg, "jpg", baos);
		return cutCenterImage(baos.toByteArray(), w, h);
	}
}
