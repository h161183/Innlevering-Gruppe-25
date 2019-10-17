package no.hvl.dat100ptc.oppgave5;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

import javax.swing.JOptionPane;

public class ShowProfile extends EasyGraphics {

	private static int MARGIN = 50; // margin on the sides

	// FIXME: use highest point and scale accordingly
	private static int MAXBARHEIGHT = 500; // assume no height above 500 meters

	private GPSPoint[] gpspoints;

	public ShowProfile() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		GPSComputer gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void run() {

		int N = gpspoints.length; // number of data points

		makeWindow("Height profile", 2 * MARGIN + 3 * N, 2 * MARGIN + MAXBARHEIGHT);

		// top margin + height of drawing area
		showHeightProfile(MARGIN + MAXBARHEIGHT);
	}

	public void showHeightProfile(int ybase) {

		// ybase indicates the position on the y-axis where the columns should start

		// TODO - START

		int tidsskalering = Integer.parseInt(getText("Tidsskalering"));
		setColor(0, 0, 255);

		for (int i = 0; i < gpspoints.length; i++) {
			int x1, x2, y1, y2;

			x1 = i * 2 + MARGIN;
			x2 = x1;

			y1 = ybase;

			y2 = ybase - (int) Math.ceil(Double.max(gpspoints[i].getElevation(), 0.0));

			drawLine(x1, y1, x2, y2);

		}

		// TODO - SLUTT
	}

}
