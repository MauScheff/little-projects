
public class Media {
	
	/*
	 * Attributes
	 */
	private static int _bookCounter=0;
	private static int _cdCounter=0;
	private static int _dvdCounter=0;
	private static double _bookCost=0;
	private static double _cdCost=0;
	private static double _dvdCost=0;
	
	private String _type="";
	private String _title="";
	private String _author="";
	private int _count=0;
	private double _cost=0;
	
	/*
	 * Constructor
	 */
	public Media(String type, String title, String author, int count, double cost){
		this._type = type;
		this._title= title;
		this._author= author;
		this._count= count;
		this._cost= cost;
	}
	
	public String readType(){
		return this._type;
	}
	
	public String readTitle(){
		return this._title;
	}
	
	public String readAuthor(){
		return this._author;
	}
	
	public int readCount(){
		return this._count;
	}
	
	public double readCost(){
		return this._cost;
	}
	
	public void addToCounter(int count){
		if (this._type.equals("book")) Media._bookCounter+=count;
		else if  (this._type.equals("cd")) Media._cdCounter+=count;
		else if (this._type.equals("dvd")) Media._dvdCounter+=count;
	}
	
	public void addToCost(int count, double cost){
		if (this._type.equals("book")) Media._bookCost+=(count*cost);
		else if  (this._type.equals("cd")) Media._cdCost+=(count*cost);
		else if (this._type.equals("dvd")) Media._dvdCost+=(count*cost);
	}
	
	public static int readBookCounter(){
		return Media._bookCounter;
	}
	
	public static int readcdCounter(){
		return Media._cdCounter;
	}
	
	public static int readdvdCounter(){
		return Media._dvdCounter;
	}
	
	public static double readBookCost(){
		return Media._bookCost;
	}
	
	public static double readCdCost(){
		return Media._cdCost;
	}
	
	public static double readDvdCost(){
		return Media._dvdCost;
	}
	
}
