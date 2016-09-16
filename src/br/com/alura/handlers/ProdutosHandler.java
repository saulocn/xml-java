package br.com.alura.handlers;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import br.com.alura.model.Produto;

public class ProdutosHandler extends DefaultHandler{
	
	private StringBuilder conteudo;
	private List<Produto> produtos = new ArrayList<>();
	private Produto produto;

	
	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		//	System.out.println("Abriu a tag: "+qName);
		conteudo = new StringBuilder();
		if(qName.equals("produto")){
			produto = new Produto();
		}
	}
	
	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		conteudo.append(new String(ch, start, length));
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if(qName.equals("produto")){
			produtos.add(produto);
		} else if (qName.equals("nome")) {
			produto.setNome(conteudo.toString());
		} else if (qName.equals("preco")) {
			produto.setPreco(Double.parseDouble(conteudo.toString()));
		}
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}
	
	
}
