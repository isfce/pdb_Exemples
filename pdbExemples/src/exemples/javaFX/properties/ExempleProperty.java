package exemples.javaFX.properties;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ExempleProperty {

	public static void main(String[] args) {
	IntegerProperty i1= new SimpleIntegerProperty(10);
//	i1.addListener(new ChangeListener<Number>() {
//
//		@Override
//		public void changed(ObservableValue<? extends Number> observable, 
//				Number oldValue, Number newValue) {
//			System.out.println(oldValue+"  ==> "+newValue);
//
//		}});
 // Version Lambda expression
	i1.addListener((observable,oldValue,newValue)->
	                 System.out.println(oldValue+"  ==> "+newValue));
	i1.addListener((o,a,n)->{
		System.out.println("Oh un changement");
	});
			
	System.out.println(i1.get());
	for (int i=1;i<10;i++)
	i1.set(i);
	
	}

}
