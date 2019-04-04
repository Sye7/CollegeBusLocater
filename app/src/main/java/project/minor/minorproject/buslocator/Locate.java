package project.minor.minorproject.buslocator;


public class Locate {
    public Double lati;
    public Double longi;

    public Locate(Double lat, Double aLong) {
        lati = lat;
        longi = aLong;
    }

    public Locate() {
        //EMPTY CONSTRUCTOR
    }

    public Double getLati() {
        return lati;
    }

    public Double getLongi() {
        return longi;
    }

    public void setLati(Double lati) {
        this.lati = lati;
    }

    public void setLongi(Double longi) {
        this.longi = longi;
    }
}
