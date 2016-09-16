package br.com.alura.teste;

import java.io.File;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import br.com.alura.model.jaxb.Produto;
import br.com.alura.model.jaxb.Venda;

public class MapearXml {
	public static void main(String[] args) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(Venda.class);
		xmlParaObjeto(jaxbContext);
		objetoParaXml(jaxbContext);
	}

	private static void objetoParaXml(JAXBContext jaxbContext) throws JAXBException {
		Marshaller marshaller = jaxbContext.createMarshaller();

		Venda venda = new Venda();
		List<Produto> produtos = new ArrayList<>();

		produtos.add(new Produto("Caneca de Java", 35.0));
		produtos.add(new Produto("Livro MEAN Stack", 70.35));
		produtos.add(new Produto("Livro Hibernate", 43.4));
		venda.setFormaDePagamento("Dinheiro");
		venda.setProdutos(produtos);
		StringWriter writer = new StringWriter();
		marshaller.marshal(venda, new File("src/vendaNova.xml"));
		marshaller.marshal(venda, writer);
		System.out.println(writer.toString());

	}

	private static void xmlParaObjeto(JAXBContext jaxbContext) throws JAXBException {
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		Venda venda = (Venda) unmarshaller.unmarshal(new File("src/vendas.xml"));
		System.out.println(venda);

	}
}
