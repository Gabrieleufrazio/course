package model.services;
 
import model.entitites.CarRental;
import model.entitites.Invoice;

public class RentalService {

	private Double pricePerDay;
	private Double pricePerHour;

	public <BrazilTaxService> RentalService(Double pricePerDay, Double pricePerHour, BrasilTaxService taxService) {
		this.pricePerDay = pricePerDay;
		this.pricePerHour = pricePerHour;
	}

	public void processInvoice(CarRental carRental) {
		long t1 = carRental.getStart().getTime();
		long t2 = carRental.getFinish().getTime();
		double hours = (double)(t2 - t1) / 1000 / 60 / 60;

		double basicPayment;
		if (hours <= 12.0) {
			basicPayment = pricePerHour * Math.ceil(hours);
		}
		else {
			basicPayment = pricePerDay * Math.ceil(hours / 24);
		}

		double tax = RentalService.tax(basicPayment);

		carRental.setInvoice(new Invoice(basicPayment, tax));
	}

	private static double tax(double basicPayment) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
