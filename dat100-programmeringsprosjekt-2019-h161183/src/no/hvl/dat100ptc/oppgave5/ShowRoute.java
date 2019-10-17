package no.hvl.dat100ptc.oppgave5;

import javax.swing.JOptionPane;

import easygraphics.EasyGraphics;
import no.hvl.dat100ptc.TODO;
import no.hvl.dat100ptc.oppgave1.GPSPoint;
import no.hvl.dat100ptc.oppgave3.GPSUtils;
import no.hvl.dat100ptc.oppgave4.GPSComputer;

public class ShowRoute extends EasyGraphics {

	private static int MARGIN = 50;
	private static int MAPXSIZE = 800;
	private static int MAPYSIZE = 800;

	private GPSPoint[] gpspoints;
	private GPSComputer gpscomputer;

	public ShowRoute() {

		String filename = JOptionPane.showInputDialog("GPS data filnavn: ");
		gpscomputer = new GPSComputer(filename);

		gpspoints = gpscomputer.getGPSPoints();

	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void run() {

		makeWindow("Route", MAPXSIZE + 2 * MARGIN, MAPYSIZE + 2 * MARGIN);

		showRouteMap(MARGIN + MAPYSIZE);

		playRoute(MARGIN + MAPYSIZE);

		showStatistics();
	}

	// antall x-pixels per lengdegrad
	public double xstep() {

		double maxlon = GPSUtils.findMax(GPSUtils.getLongitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = MAPXSIZE / (Math.abs(maxlon - minlon));

		return xstep;
	}

	// antall y-pixels per breddegrad
	public double ystep() {

		double ystep;

		// TODO - START

		double maxlad = GPSUtils.findMax(GPSUtils.getLatitudes(gpspoints));
		double minlad = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));

		ystep = MAPYSIZE / (Math.abs(maxlad - minlad));

		return ystep;

		// TODO - SLUTT

	}

	public void showRouteMap(int ybase) {

		// TODO - START
		double minlad = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		setColor(0, 255, 0);
		for (int i = 0; i < gpspoints.length; i++) {
			int x = MARGIN + (int) ((gpspoints[i].getLongitude() - minlon) * xstep());
			int y = ybase - (int) ((gpspoints[i].getLatitude() - minlad) * ystep());
			fillCircle(x, y, 3);

		}

		// TODO - SLUTT
	}

	public void showStatistics() {

		int TEXTDISTANCE = 20;

		setColor(0, 0, 0);
		setFont("Courier", 12);

		// TODO - START

		String time = ("Total Time\t:\t") + GPSUtils.formatTime(gpscomputer.totalTime());
		String distance = ("Total Distance\t:\t") + GPSUtils.formatDouble(gpscomputer.totalDistance()) + (" km");
		String Elevation = ("Total Elevation\t:\t") + GPSUtils.formatDouble(gpscomputer.totalElevation()) + (" m");
		String MaxSpeed = ("Max Speed\t:\t") + GPSUtils.formatDouble(gpscomputer.maxSpeed()) + (" km/t");
		String AvgSpeed = ("Average Speed\t:\t") + GPSUtils.formatDouble(gpscomputer.averageSpeed()) + (" km/t");
		String Energy = ("Energy\t:\t") + GPSUtils.formatDouble(gpscomputer.totalKcal(80)) + (" kcal");

		drawString(time, TEXTDISTANCE, 20);
		drawString(distance, TEXTDISTANCE, 35);
		drawString(Elevation, TEXTDISTANCE, 50);
		drawString(MaxSpeed, TEXTDISTANCE, 65);
		drawString(AvgSpeed, TEXTDISTANCE, 80);
		drawString(Energy, TEXTDISTANCE, 95);

		// TODO - SLUTT;
	}

	public void playRoute(int ybase) {

		// TODO - START

		double minlad = GPSUtils.findMin(GPSUtils.getLatitudes(gpspoints));
		double minlon = GPSUtils.findMin(GPSUtils.getLongitudes(gpspoints));

		double xstep = xstep();
		double ystep = ystep();

		setColor(0, 0, 255);

		int x = MARGIN + (int) ((gpspoints[0].getLongitude() - minlon) * xstep());
		int y = ybase - (int) ((gpspoints[0].getLatitude() - minlad) * ystep());
		int movingCircle = fillCircle(x, y, 3);

		setColor(0, 0, 255);

		// TODO - SLUTT
	}

}
