package java;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;   //엉뚱한 Document 클래스 땡겨오면 나처럼 한시간 썩음
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class XmlParsing {

	/** 
	 * String 형태로 넘어온 XML 데이터 파싱하기.
	 * 
	 * @author 수습 연구원 조현권
	 * @param XML 형태로 넘어온 String Data
	 * @throws 실패
	 * @date 2015/04/21
	 * */
	public static void main(String[] args) {
		// TODO String형태로 넘어온 XML데이터를 파싱하여 처리하여보자!
		
		// 실제 행정안전부 신 구주소 교차검색 API 요청시 넘어오는 xml 형태의 String data
		String strXml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root><common><totalCount>667</totalCount><currentPage>1</currentPage><countPerPage>10</countPerPage><errorCode>0</errorCode><errorMessage>정상</errorMessage></common><item><roadAddr><![CDATA[서울특별시 마포구 월드컵로 243-60 (성산동)]]></roadAddr><roadAddrPart1><![CDATA[서울특별시 마포구 월드컵로 243-60]]></roadAddrPart1><roadAddrPart2><![CDATA[ (성산동)]]></roadAddrPart2><jibunAddr><![CDATA[서울특별시 마포구 성산동   390-1 월드컵공원관리사업소]]></jibunAddr><engAddr><![CDATA[243-60, World Cup-ro, Mapo-gu, Seoul]]></engAddr><zipNo><![CDATA[121-850]]></zipNo><admCd><![CDATA[1144012500]]></admCd><rnMgtSn><![CDATA[114403113018]]></rnMgtSn><bdMgtSn><![CDATA[1144012500103900001017938]]></bdMgtSn></item><item><roadAddr><![CDATA[서울특별시 마포구 월드컵로 243-48 (성산동)]]></roadAddr><roadAddrPart1><![CDATA[서울특별시 마포구 월드컵로 243-48]]></roadAddrPart1><roadAddrPart2><![CDATA[ (성산동)]]></roadAddrPart2><jibunAddr><![CDATA[서울특별시 마포구 성산동   390-1 매점]]></jibunAddr><engAddr><![CDATA[243-48, World Cup-ro, Mapo-gu, Seoul]]></engAddr><zipNo><![CDATA[121-850]]></zipNo><admCd><![CDATA[1144012500]]></admCd><rnMgtSn><![CDATA[114403113018]]></rnMgtSn><bdMgtSn><![CDATA[1144012500103900001129274]]></bdMgtSn></item><item><roadAddr><![CDATA[서울특별시 마포구 월드컵로 251 (성산동)]]></roadAddr><roadAddrPart1><![CDATA[서울특별시 마포구 월드컵로 251]]></roadAddrPart1><roadAddrPart2><![CDATA[ (성산동)]]></roadAddrPart2><jibunAddr><![CDATA[서울특별시 마포구 성산동   390-1 서울시생활체육협의회]]></jibunAddr><engAddr><![CDATA[251, World Cup-ro, Mapo-gu, Seoul]]></engAddr><zipNo><![CDATA[121-850]]></zipNo><admCd><![CDATA[1144012500]]></admCd><rnMgtSn><![CDATA[114403113018]]></rnMgtSn><bdMgtSn><![CDATA[1144012500103900001017989]]></bdMgtSn></item><item><roadAddr><![CDATA[서울특별시 마포구 성암로 223-1 (상암동)]]></roadAddr><roadAddrPart1><![CDATA[서울특별시 마포구 성암로 223-1]]></roadAddrPart1><roadAddrPart2><![CDATA[ (상암동)]]></roadAddrPart2><jibunAddr><![CDATA[서울특별시 마포구 상암동   2-10]]></jibunAddr><engAddr><![CDATA[223-1, Seongam-ro, Mapo-gu, Seoul]]></engAddr><zipNo><![CDATA[121-830]]></zipNo><admCd><![CDATA[1144012700]]></admCd><rnMgtSn><![CDATA[114403113012]]></rnMgtSn><bdMgtSn><![CDATA[1144012700100020010000001]]></bdMgtSn></item><item><roadAddr><![CDATA[서울특별시 마포구 성암로 219-2 (상암동)]]></roadAddr><roadAddrPart1><![CDATA[서울특별시 마포구 성암로 219-2]]></roadAddrPart1><roadAddrPart2><![CDATA[ (상암동)]]></roadAddrPart2><jibunAddr><![CDATA[서울특별시 마포구 상암동   2-26]]></jibunAddr><engAddr><![CDATA[219-2, Seongam-ro, Mapo-gu, Seoul]]></engAddr><zipNo><![CDATA[121-830]]></zipNo><admCd><![CDATA[1144012700]]></admCd><rnMgtSn><![CDATA[114403113012]]></rnMgtSn><bdMgtSn><![CDATA[1144012700100020026000001]]></bdMgtSn></item><item><roadAddr><![CDATA[서울특별시 마포구 성암로 219-4 (상암동)]]></roadAddr><roadAddrPart1><![CDATA[서울특별시 마포구 성암로 219-4]]></roadAddrPart1><roadAddrPart2><![CDATA[ (상암동)]]></roadAddrPart2><jibunAddr><![CDATA[서울특별시 마포구 상암동   2-28]]></jibunAddr><engAddr><![CDATA[219-4, Seongam-ro, Mapo-gu, Seoul]]></engAddr><zipNo><![CDATA[121-830]]></zipNo><admCd><![CDATA[1144012700]]></admCd><rnMgtSn><![CDATA[114403113012]]></rnMgtSn><bdMgtSn><![CDATA[1144012700100020028018497]]></bdMgtSn></item><item><roadAddr><![CDATA[서울특별시 마포구 성암로 219-6 (상암동)]]></roadAddr><roadAddrPart1><![CDATA[서울특별시 마포구 성암로 219-6]]></roadAddrPart1><roadAddrPart2><![CDATA[ (상암동)]]></roadAddrPart2><jibunAddr><![CDATA[서울특별시 마포구 상암동   2-29]]></jibunAddr><engAddr><![CDATA[219-6, Seongam-ro, Mapo-gu, Seoul]]></engAddr><zipNo><![CDATA[121-830]]></zipNo><admCd><![CDATA[1144012700]]></admCd><rnMgtSn><![CDATA[114403113012]]></rnMgtSn><bdMgtSn><![CDATA[1144012700100020029018498]]></bdMgtSn></item><item><roadAddr><![CDATA[서울특별시 마포구 성암로 219-10 (상암동)]]></roadAddr><roadAddrPart1><![CDATA[서울특별시 마포구 성암로 219-10]]></roadAddrPart1><roadAddrPart2><![CDATA[ (상암동)]]></roadAddrPart2><jibunAddr><![CDATA[서울특별시 마포구 상암동   2-30]]></jibunAddr><engAddr><![CDATA[219-10, Seongam-ro, Mapo-gu, Seoul]]></engAddr><zipNo><![CDATA[121-830]]></zipNo><admCd><![CDATA[1144012700]]></admCd><rnMgtSn><![CDATA[114403113012]]></rnMgtSn><bdMgtSn><![CDATA[1144012700100020030018500]]></bdMgtSn></item><item><roadAddr><![CDATA[서울특별시 마포구 성암로 219-9 (상암동)]]></roadAddr><roadAddrPart1><![CDATA[서울특별시 마포구 성암로 219-9]]></roadAddrPart1><roadAddrPart2><![CDATA[ (상암동)]]></roadAddrPart2><jibunAddr><![CDATA[서울특별시 마포구 상암동   2-31]]></jibunAddr><engAddr><![CDATA[219-9, Seongam-ro, Mapo-gu, Seoul]]></engAddr><zipNo><![CDATA[121-830]]></zipNo><admCd><![CDATA[1144012700]]></admCd><rnMgtSn><![CDATA[114403113012]]></rnMgtSn><bdMgtSn><![CDATA[1144012700100020031000001]]></bdMgtSn></item><item><roadAddr><![CDATA[서울특별시 마포구 성암로 219-5 (상암동)]]></roadAddr><roadAddrPart1><![CDATA[서울특별시 마포구 성암로 219-5]]></roadAddrPart1><roadAddrPart2><![CDATA[ (상암동)]]></roadAddrPart2><jibunAddr><![CDATA[서울특별시 마포구 상암동   2-33]]></jibunAddr><engAddr><![CDATA[219-5, Seongam-ro, Mapo-gu, Seoul]]></engAddr><zipNo><![CDATA[121-830]]></zipNo><admCd><![CDATA[1144012700]]></admCd><rnMgtSn><![CDATA[114403113012]]></rnMgtSn><bdMgtSn><![CDATA[1144012700100020033018501]]></bdMgtSn></item></root>";
		
		//맨 밑의 메서드 cvtStrToDoc참조.
		Document doc = cvtStrToDoc(strXml);
		
		// 어디서 많이 본 문법. 
		//NodeList를 생성하지만, 이 리스트는 Object.Array가 아님.
		NodeList nodRoadAddr = doc.getElementsByTagName("roadAddr");
		
		//해당 태그네임을 가지고 있는 Node들로 노드 배열을 만듬.
		NodeList nodJibunAddr = doc.getElementsByTagName("jibunAddr");
		
		// List가 아니라 for(Object object : List) 간지 안됨
		for(int i=0; i < nodRoadAddr.getLength(); i++) {
			int index = i + 1;
			
			//노드 배열에서 item(index)로 원하는 Node를 가져올 수 있고, 그 값은 getTextContent() <- String 반환; 로 가져옴.
			System.out.println(index+"번째 도로 주소명 : " + nodRoadAddr.item(i).getTextContent());
			System.out.println(index+"번째 지번 주소명 : " + nodJibunAddr.item(i).getTextContent());
		}
	}
	
	// 파서를 사용하지 않고 직접 제작, 나 X나 대단
	// 실무에선 SAX 파서, PULL파서, Eh anjduTwl.. 등등 사용
	private static Document cvtStrToDoc(String xmlStr) {
		//도큐먼트 빌더 팩토리를 쨘!
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        //도큐먼트 빌더를 쨘! 말고 세워두고
        DocumentBuilder builder;  
        try 
        {  
        	//쨘!! 에로나면 캐츼해야되서 여기서 쨘
            builder = factory.newDocumentBuilder();
            
            // str을 받아 StringReader로 읽어 InputSource로 만들어 퐐스.
            Document doc = builder.parse( new InputSource( new StringReader( xmlStr ) ) );
            
            //돌려줘 내 소중한 디오씨
            return doc;
            
        } catch (Exception e) {  
            //에로캐취
        	e.printStackTrace();  
        } 
        return null;
    }
}
// 느낀점 : 난 개간지
// 내가 짠 컨트롤러를 가져오고 싶지만, 정보서약서에 서약을 해서 기트에 못올림 유유.
