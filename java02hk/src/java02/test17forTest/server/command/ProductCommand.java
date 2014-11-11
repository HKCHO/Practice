package java02.test17forTest.server.command;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import java02.test17forTest.server.Product;
import java02.test17forTest.server.ProductDao;
import java02.test17forTest.server.annotation.Command;
import java02.test17forTest.server.annotation.Component;

@Component
public class ProductCommand {
	ProductDao productDao;
	Scanner scanner;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	public void setScanner(Scanner scanner) {
		this.scanner = scanner;
	}


	private HashMap<String,String> parseQueryString(String query){
		//예)query->  name=제품명&qty=20&mkno=6
		// => {"name=제품명", "qty=20", "mkno=6"}
		String[] entryList = query.split("&");
		HashMap<String,String> tempMap = new HashMap<>();

		String[] token = null;
		for (String entry : entryList){
			token = entry.split("=");
			tempMap.put(token[0],token[1]);
		}
		return tempMap;
	}

	@Command("add")
	public void add(Map<String, Object> params) {
		PrintStream out = (PrintStream)params.get("out");
		try {
			ArrayList<String> options =
					(ArrayList<String>)params.get("options");

			HashMap<String,String> valueMap =
					parseQueryString(options.get(0));
			
			Product product = new Product();
			product.setName(valueMap.get("name"));
			product.setQuantity(Integer.parseInt(valueMap.get("qty")));
			product.setMakerNo(Integer.parseInt(valueMap.get("mkno")));
			
			productDao.insert(product);
			out.println("저장하였습니다.");
			out.println();
			
		} catch (Exception e) {
			e.printStackTrace();
			out.println("서버가 바쁩니다. 잠시 후 다시 시도하세요");
			out.println();
		}
	}

	@Command("delete")
	public void delete(Map<String, Object> params) {
		@SuppressWarnings("unchecked")
		ArrayList<String> options = 
		(ArrayList<String>)params.get("options");

		int no = Integer.parseInt(options.get(0));

		Product product = productDao.selectOne(no);
		if (product == null) {
			System.out.println("해당 번호의 제품 정보를 찾을 수 없습니다.");
			return;
		}

		System.out.print(product.getName() + "을 삭제하시겠습니까?(y/n)");
		if (scanner.nextLine().equalsIgnoreCase("y")) {
			productDao.delete(no);
			System.out.println("삭제하였습니다.");
		} else {
			System.out.println("삭제 취소하였습니다.");
		}
	}

	@Command("list")
	public void list(Map<String, Object> params) {
		@SuppressWarnings("unchecked")
		ArrayList<String> options = 
		(ArrayList<String>)params.get("options");
		

		int pageNo = 0;
		int pageSize = 0;

		if (options.size() > 0) {
			pageNo = Integer.parseInt(options.get(0));
			pageSize = 3;
		}

		if (options.size() > 1) {
			pageSize = Integer.parseInt(options.get(1));
		}

		PrintStream out = (PrintStream)params.get("out");

		for (Product product : productDao.selectList(pageNo, pageSize)) {
			out.printf("%-3d %-20s %7d %-3d\n", 
					product.getNo(), 
					product.getName(), 
					product.getQuantity(), 
					product.getMakerNo());
		}
		out.println();
	}

	@Command("update")
	public void update(Map<String, Object> params) {
		@SuppressWarnings("unchecked")
		ArrayList<String> options = 
		(ArrayList<String>)params.get("options");

		int no = Integer.parseInt(options.get(0));

		Product product = productDao.selectOne(no);
		if (product == null) {
			System.out.println("해당 번호의 제품 정보를 찾을 수 없습니다.");
			return;
		}

		Product tempProduct = null;

		try {
			tempProduct = product.clone();
		} catch (CloneNotSupportedException ex) {
			throw new RuntimeException(ex);
		}

		String text = null;
		System.out.printf("제품명(%s):", product.getName());
		text = scanner.nextLine();
		if (text.length() > 0)
			tempProduct.setName(text);

		System.out.printf("수량(%d):", product.getQuantity());
		text = scanner.nextLine();
		if (text.length() > 0)
			tempProduct.setQuantity(Integer.parseInt(text));

		System.out.printf("제조사 번호(%d):", product.getMakerNo());
		text = scanner.nextLine();
		if (text.length() > 0)
			tempProduct.setMakerNo(Integer.parseInt(text)); 

		System.out.print("정말 변경하시겠습니까?(y/n)");
		if (scanner.nextLine().equalsIgnoreCase("y")) {
			productDao.update(tempProduct);
			System.out.println("변경하였습니다.");
		} else {
			System.out.println("변경 취소하였습니다.");
		}
	}

	@Command("view")
	public void view(Map<String, Object> params) throws Exception {
		@SuppressWarnings("unchecked")
		ArrayList<String> options = 
		(ArrayList<String>)params.get("options");

		int no = Integer.parseInt(options.get(0));

		PrintStream out = (PrintStream)params.get("out");

		Product product = productDao.selectOne(no);
		if (product == null) {
			out.println("해당 번호의 제품 정보를 찾을 수 없습니다.");
			out.println();
			return;
		}

		out.println("제품번호: " + no);
		out.println("제품명: " + product.getName());
		out.println("수량: " + product.getQuantity());
		out.println("제조사 번호: " + product.getMakerNo());
		out.println();

	}
}








