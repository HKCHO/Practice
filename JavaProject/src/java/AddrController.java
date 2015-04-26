/* 참조용 
package java;

import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/sch")
public class AddrController {
	
	@RequestMapping("/schAddr")
	public void schAddr(HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println("schAddr 컨트롤러 동작 시작");

		<<비밀!>>

		final String countPerPage = "10";																// 페이지당 아이템 개수
		final String confirmKey = <<비밀>>			// 인증키(www.juso.go.kr 4/21 발급 : hk-cho)

		//넘어올 파라미터
		//String currentPage	= interfaceData.get("currentPage").toString();						// 현재 페이지 넘버
		//String keyword			= interfaceData.get("keyword").toString();								// 검색 키워드

		String currPage = "1";
		String schUrl = "http://www.juso.go.kr/addrlink/addrLinkApi.do?currentPage="+ currPage
							+"&countPerPage="+countPerPage
							+"&keyword="+URLEncoder.encode("마포구 상암동","UTF-8")
							+"&confmKey="+confirmKey;

		URL url = new URL(schUrl);
		BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
		StringBuffer sb = new StringBuffer();
		String tempStr = null;

		while(true){
    		tempStr = br.readLine();
    		if(tempStr == null) break;
    		sb.append(tempStr);	
    	}
		br.close();
		
		String xmlString = sb.toString();
		System.out.println(xmlString);
		
		String strJusoXml = xmlString.replaceAll("<juso>","<item>")
												.replaceAll("</juso>", "</item>")
												.replaceAll("<results>", "<root>")
												.replaceAll("</results>", "</root>");
		
		System.out.println(strJusoXml);
		
		Document doc = cvtStrToDoc(strJusoXml);
		
		NodeList nodRoadAddr = doc.getElementsByTagName("roadAddr");
		NodeList nodJibunAddr = doc.getElementsByTagName("jibunAddr");
		//NodeList nodJibunAddr = doc.getElementsByTagName("");
		
		System.out.println(currPage + "번째 페이지 검색결과\n");
		for(int i=0; i < nodRoadAddr.getLength(); i++) {
			int index = i + 1;
			System.out.println(index+"번째 도로 주소명 : " + nodRoadAddr.item(i).getTextContent());
			System.out.println(index+"번째 지번 주소명 : " + nodJibunAddr.item(i).getTextContent());
		}
		String xml ="<root>";
    	xml+="<response>"+ "00" + "</response>";
    	xml+="<data>";
    	xml+=strJusoXml;
    	xml+="</data>";
    	xml+="</root>"; 삭제할 것 
		//<<비밀>>(response, interfaceData.get("skey").toString(), sb.toString());
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/xml");
		
		*//**************************************
		 * DO DELETE AFTER DONE : hk-cho
		 * 
		 * -RESPONSE DATA FORMAT-
		 * 
		 * totalCount : 총 검색 데이터 수   623
		 * currentPage : 현재 페이지 번호  10000
		 * countPerPage : 페이지당 출력할 ROW  10
		 * errorCode
		 * errorMessage
		 * roadAddr :전체 도로명 주소
		 * roadAddrPart1 : 도로명 주소
		 * roadAddrPart2 : 도로명주소상세
		 * jibunAddr : 지번주소
		 * engAddr : 도로명주소(영문)
		 * zipNo : 우편번호
		 * admCd : 행정구역코드
		 * rnMgtSn : 도로명코드
		 * bdMgtSn : 건물관리번호
		 ***************************************//*
	}
}
*/