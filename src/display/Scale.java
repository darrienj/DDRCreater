package display;

public class Scale {

	double size;
	public void setSize(double size){
		this.size = size;
	}
	public int scale(double percent){
		return (int)(percent*size);
	}
}
