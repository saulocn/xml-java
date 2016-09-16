package br.com.alura.teste;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import br.com.alura.model.Produto;

public class LeArquivoComStax {
	public static void main(String[] args) throws FileNotFoundException, XMLStreamException {
		InputStream is = new FileInputStream("src/vendas.xml");
		XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
		XMLEventReader eventos = xmlInputFactory.createXMLEventReader(is);
		
		Produto produto = new Produto();
		List<Produto> produtos = new ArrayList<>();

		while (eventos.hasNext()) {
			// pega produto
			XMLEvent evento = eventos.nextEvent();
			// se for produto, cria o produto
			if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("produto")) {
				produto = new Produto();
			} else if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("nome")) {
				evento = eventos.nextEvent();
				String nome = evento.asCharacters().getData();
				produto.setNome(nome);
			} else if (evento.isStartElement() && evento.asStartElement().getName().getLocalPart().equals("preco")) {
				evento = eventos.nextEvent();
				double preco = Double.parseDouble(evento.asCharacters().getData());
				produto.setPreco(preco);
			} else if (evento.isEndElement() && evento.asEndElement().getName().getLocalPart().equals("produto")) {
				produtos.add(produto);
			}
		}
		
		
		System.out.println(produtos);
	}
}
