package modelo;

import java.util.Calendar;
import java.util.Date;

public class Item {
	
	private Livro livro;
    private Date dataDevolucao;
    
    public Item(Livro livro) {
		super();
		this.livro = livro;	
	}
    
    public Date calculaDataDevolucao(Date data)
	{  dataDevolucao=data;
	   Calendar calendar = Calendar.getInstance();
	   calendar.setTime(data);
	   calendar.add(Calendar.DATE, livro.verPrazo());
	   dataDevolucao = calendar.getTime();
	   return dataDevolucao;
	}
    
    public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}
	
	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}	
}