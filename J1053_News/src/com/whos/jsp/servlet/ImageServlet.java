package com.whos.jsp.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.mail.handlers.image_gif;

public class ImageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//������֤���ͼƬ
		BufferedImage img = new BufferedImage(60,30,BufferedImage.TYPE_INT_RGB);
		//��ͼ���� Graphics
		Graphics g = img.getGraphics();
		g.setColor(new Color(255,255,255));
		g.fillRect(0, 0, img.getWidth(), img.getHeight());
		g.setColor(new Color(0,0,0));
		g.drawRect(0, 0, img.getWidth()-1, img.getHeight()-1);
		
		Random random = new Random();
		int num = 1000+random.nextInt(9000);
		String str = String.valueOf(num);
		request.getSession().setAttribute("code", str);
		//���ø�ʽ
		g.setFont(new Font("����", Font.BOLD, 20));
		//������
		g.drawString(str, 8, 23);
		
		//��ɧ����
		for(int i=0;i<15;i++){
			int x1 = random.nextInt(img.getWidth());
			int y1 = random.nextInt(img.getHeight());
			int x2 = random.nextInt(img.getWidth());
			int y2 = random.nextInt(img.getHeight());
			g.drawLine(x1, y1, x2, y2);
		}
		
		//ͼƬ������ͻ���
		ImageIO.write(img, "JPEG", response.getOutputStream());

	}

}
