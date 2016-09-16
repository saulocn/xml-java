package br.com.alura.teste;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import br.com.alura.model.Produto;

public class LeArquivoEFiltraComXPath {
	public static void main(String[] args) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(true);
		factory.setNamespaceAware(true);
		factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage",
				"http://www.w3.org/2001/XMLSchema");
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse("src/vendas.xml");

		Element venda = document.getDocumentElement();
		String moeda = venda.getAttribute("moeda");
		System.out.println(moeda);
		String formaDePagamento = venda.getElementsByTagName("formaDePagamento").item(0).getTextContent();
		System.out.println(formaDePagamento);

		// Como se fosse um diretório
		//String exp = "/venda/produtos/produto";
		
		//Somente o segundo produto
		//String exp = "/venda/produtos/produto[2]";
		
		// Nome IGUAL a 'Livro de XML'
		//String exp = "/venda/produtos/produto[nome='Livro de XML']";
		
		// Contém a palavra
		String exp = "/venda/produtos/produto[contains(nome,'Java')]";

		XPath path = XPathFactory.newInstance().newXPath();
		XPathExpression expression = path.compile(exp);

		// NodeList produtos = document.getElementsByTagName("produto");
		NodeList produtos = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
		for (int i = 0; i < produtos.getLength(); i++) {
			Element produto = (Element) produtos.item(i);
			String nome = produto.getElementsByTagName("nome").item(0).getTextContent();
			double preco = Double.parseDouble(produto.getElementsByTagName("preco").item(0).getTextContent());
			Produto prod = new Produto(nome, preco);
			System.out.println(prod);
		}

	}
}
