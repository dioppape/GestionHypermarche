package fr.services.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import fr.models.*;
import fr.services.CrudArticle;

@Service
public class CrudArticleImpl implements CrudArticle {
	@Autowired
	JAXBContext context = null;
	private String outputFile="article.xml";
	private String packages="fr.models";
	Marshaller marsh;
	ObjectFactory factory;
	Articles hyper;
	//Categorie categorie;
	//Rayon rayon;
	private List<Article> listArticle;
	private List<Article> list;
	public CrudArticleImpl(){
     factory=new ObjectFactory();
	 hyper=factory.createArticles();
	}
	@Override
	public String updateArticle(Article article) {
		try {
		context=JAXBContext.newInstance(packages);
		Unmarshaller unmarsh=context.createUnmarshaller();
		File file=new File(outputFile);
		if(file.exists()){
		hyper=(Articles) unmarsh.unmarshal(file);
		 list=hyper.getArticle();
		}
		else{
			hyper=new Articles();
			 list=new ArrayList<Article>();
		}
		//add article
		
		
		boolean existe=false;
		int i=0;
		while(i<list.size()){

			if(list.get(i).getCodebar().equals(article.getCodebar())){
				//list.get(i).setId(rayon.getId());
				list.set(i, article);
				 existe= true;
			}
			i++;
		}
		if(!existe)
		list.add(article);
	 
		marsh = context.createMarshaller();
		marsh.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
		marsh.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marsh.marshal(hyper, new File(outputFile));

	} catch (JAXBException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
return null;
}


	public List<Article> getArticleByCat(Categorie categorie) {
		
	
			try {
				context=JAXBContext.newInstance(packages);
				Unmarshaller unmarsh=context.createUnmarshaller();
				 hyper=(Articles) unmarsh.unmarshal(new File(outputFile));
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		return hyper.getArticle();
	}
	@Override
	public List<Article> getArticleByCategorie(Categorie categorie) {
		 listArticle=new ArrayList<Article>();
		try {
			context=JAXBContext.newInstance(packages);
			Unmarshaller unmarsh=context.createUnmarshaller();
			 hyper=(Articles) unmarsh.unmarshal(new File(outputFile));
				list=hyper.getArticle();
				int i=0;
				while(i<list.size()){
				if(list.get(i).getCategorie().getId().equals(categorie.getId()))
					listArticle.add(list.get(i));
				i++;
				}
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listArticle;
	}

	@Override
	public List<Article> getAllArticle() {
		try {
			context=JAXBContext.newInstance(packages);
			Unmarshaller unmarsh=context.createUnmarshaller();
			 hyper=(Articles) unmarsh.unmarshal(new File(outputFile));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	return hyper.getArticle();
	}
	
	@Override
	public String deleteArticle(Article article) {
		String result="nothing to update";
		try {
			context=JAXBContext.newInstance(packages);
			Unmarshaller unmarsh=context.createUnmarshaller();
			hyper=(Articles) unmarsh.unmarshal(new File(outputFile));
			list=hyper.getArticle();
			int i=0;
			while(i<list.size()){
			if(list.get(i).getCodebar().equals(article.getCodebar()))
				list.remove(i);
			i++;
		}
			marsh = context.createMarshaller();
			marsh.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
			marsh.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marsh.marshal(hyper, new File(outputFile));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	public String deleteArticle(String id) {
		String result="nothing to update";
		try {
			context=JAXBContext.newInstance(packages);
			Unmarshaller unmarsh=context.createUnmarshaller();
			hyper=(Articles) unmarsh.unmarshal(new File(outputFile));
			list=hyper.getArticle();
			
			int i = list.size()-1;
			while(i>=0){
			if(list.get(i).getCategorie().getId().equals(id)){
				list.remove(i);
			
			}
			i--;
		}
			marsh = context.createMarshaller();
			marsh.setProperty(javax.xml.bind.Marshaller.JAXB_ENCODING, "UTF-8"); //NOI18N
			marsh.setProperty(javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marsh.marshal(hyper, new File(outputFile));
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

@Override
public Article getArticle(String codeBar) {
Article article = null;
try {
	context=JAXBContext.newInstance(packages);
	Unmarshaller unmarsh=context.createUnmarshaller();
	 hyper=(Articles) unmarsh.unmarshal(new File(outputFile));
	 list=hyper.getArticle();
		int i=0;
		while(i<list.size()){
		if(list.get(i).getCodebar().equals(codeBar))
			article=list.get(i);
		i++;
		}
} catch (JAXBException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
return article;
}

public Article getArticleDom(String codeBar) {
	
	XPathFactory pathFactory=XPathFactory.newInstance();
	XPath xpath = pathFactory.newXPath();
	try {
		XPathExpression expr = xpath.compile("//Rayon/Categorie/Article[@CodeBar="+codeBar+"]");
		//XPathExpression expr = xpath.compile("//Rayon/Categorie/Article");
		File fXmlFile = new File(outputFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		// use DTD validation
		dbFactory.setValidating(true);
		
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		// report Errors if DTD validation is On
		 // note: ErrorHandler must be defined
		
		dBuilder.setErrorHandler(null);
	 Document doc = dBuilder.parse(fXmlFile);
	doc.getDocumentElement().normalize();
		
		NodeList nList = (NodeList)expr.evaluate(doc,XPathConstants.NODESET);
		 System.out.println(nList.getLength());
		 for (int i = 0; i < nList.getLength(); i++) {
		Node nNode = nList.item(i);
			
		// your code here
		System.out.println("name= "+nNode.getAttributes().item(i).toString());
			 //System.out.println(nNode);
	
		 }
		 } catch (XPathExpressionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
public List<Article> getArticle() {
	
	Node nNode=null;
	XPathFactory pathFactory=XPathFactory.newInstance();
	XPath xpath = pathFactory.newXPath();
	NodeList nList=null;
	try {
		XPathExpression expr = xpath.compile("//Rayon/Categorie/Article");
		//XPathExpression expr = xpath.compile("//Rayon/Categorie/Article");
		File fXmlFile = new File(outputFile);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		// use DTD validation
		dbFactory.setValidating(true);
		
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		// report Errors if DTD validation is On
		 // note: ErrorHandler must be defined
		
		dBuilder.setErrorHandler(null);
	 Document doc = dBuilder.parse(fXmlFile);
	doc.getDocumentElement().normalize();
		
		nList = (NodeList)expr.evaluate(doc,XPathConstants.NODESET);
		 System.out.println(nList.getLength());
		 for (int i = 0; i < nList.getLength(); i++) {
		nNode = nList.item(i);
			
		// your code here
		//System.out.println("name= "+nNode.getAttributes().item(i).toString());
			 System.out.println(nNode.getAttributes());
	
		 }
		 } catch (XPathExpressionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
}
}
