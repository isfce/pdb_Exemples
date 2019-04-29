package exemples.javaFX.properties;

import javax.swing.JOptionPane;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.IntegerBinding;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class ExemplesBinding {

	public static void main(String[] args) {
		//Utilisation de l'API Fluent
		IntegerProperty prop1 = new SimpleIntegerProperty (10) ;
		IntegerProperty prop2 = new SimpleIntegerProperty (20) ;
		prop1.bind( prop2 );// prop1 dépendra de la propriété propr2
		System .out . println ( prop1.get() + " " + prop2.get());// prop1 est unbound
		prop2.set(50);
		System .out . println ( prop1.get() + " " + prop2.get());
		//System .out . println ( prop1.get ());// prop1 est réévaluer au get ()
		//System .out . println ( prop1 + " " + prop2 );// prop1 est bien à jour
		JOptionPane.showMessageDialog(null,"suite");
		//Utilisation de la classe Binding
		IntegerProperty prop3 = new SimpleIntegerProperty (10) ;
		IntegerProperty prop4 = new SimpleIntegerProperty (20) ;
		NumberBinding somme = Bindings.add(prop3 , prop4 );
		System .out . println (" somme : "+ somme . getValue ()+" "+ prop3 + " " + prop4);
		prop4.set(50) ;// change la valeur de prop2
		
		System .out . println (" somme : "+ somme . getValue ()+" "+ prop3 + " " + prop4);
		JOptionPane.showMessageDialog(null,"suite");
		
		//Binding de bas niveau
		IntegerProperty prop5 = new SimpleIntegerProperty (17) ;
		IntegerProperty prop6 = new SimpleIntegerProperty (3);
		IntegerBinding expr1 = new IntegerBinding () {
		{// construction
		super.bind (prop5 , prop6 );
		}
		@Override
		protected int computeValue () {
		// calcul le modulo
		return prop5.get () % prop6.get ();
		}
		};
		System.out.println(" modulo : " + expr1.getValue () + " " + prop5 + " " +
		prop6);
		prop5.set (22) ;
		System.out.println(" modulo : " + expr1.getValue () + " " + prop5 + " " +
		prop6 );
	}

}
