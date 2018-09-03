package Model;

public class Rango {
 private boolean pareja;
 private boolean suited;
 private boolean offsuited;
 private char tipo;
 private int valor[];
 
 
 public Rango() {
	this.pareja = false;
	this.suited = false;
	this.tipo='x';
	this.offsuited = false;
	this.valor = new int [4];
}
public boolean isPareja() {
	return pareja;
}
public void setPareja(boolean pareja) {
	this.pareja = pareja;
}
public boolean isSuited() {
	return suited;
}
public void setSuited(boolean suited) {
	this.suited = suited;
}
public boolean isOffsuited() {
	return offsuited;
}
public void setOffsuited(boolean offsuited) {
	this.offsuited = offsuited;
}
public char getTipo() {
	return tipo;
}
public void setTipo(char tipo) {
	this.tipo = tipo;
}
public int getValorAt(int i) {
	return valor[i];
}
public void setValor(int [] valor) {
	this.valor = valor;
}

}
