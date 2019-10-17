package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import com.sun.xml.internal.ws.server.sei.EndpointResponseMessageBuilder.Bare;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowSpeed extends EasyGraphics {

	private static int MARGIN = 50;
	private static int BARHEIGHT = 200; // assume no speed above 200 km/t

	private GPSComputer gpscomputer;
	private GPSPoint[] gpspoints;

	public ShowSpeed() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	// read in the files and draw into using EasyGraphics
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void run() {

		int N = gpspoints.length - 1; // number of data points

		makeWindow("Speed profile", 2 * MARGIN + 2 * N, 2 * MARGIN + BARHEIGHT);

		showSpeedProfile(MARGIN + BARHEIGHT, N);
	}

	public void showSpeedProfile(int ybase, int N) {

		System.out.println("Angi tidsskalering i tegnevinduet ...");
		int timescaling = Integer.parseInt(getText("Tidsskalering"));

		// TODO - START

		double avgspeed = gpscomputer.averageSpeed();
		setColor(0, 255, 0);
		drawLine(MARGIN, ybase - (int) avgspeed, MARGIN * 2 * N, ybase - (int) avgspeed);

		double[] speeds = gpscomputer.speeds();
		setColor(0, 0, 255);
		for (int i = 0; i < speeds.length; i++) {
			int x1, x2, y1, y2;

			x1 = i * 2 + MARGIN;
			x2 = x1;

			y1 = BARHEIGHT + MARGIN;
			y2 = MARGIN + BARHEIGHT - (int) speeds[i];

			drawLine(x1, y1, x2, y2);

		}

		// TODO - SLUTT
	}
}
