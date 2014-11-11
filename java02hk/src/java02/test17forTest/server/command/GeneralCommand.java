package java02.test17forTest.server.command;

import java.io.PrintStream;
import java.util.Map;
import java02.test17forTest.server.ProductDao;
import java02.test17forTest.server.annotation.Command;
import java02.test17forTest.server.annotation.Component;

@Component
public class GeneralCommand {
	ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Command("help")
	public void help(Map<String, Object> params) throws Exception {
		PrintStream out = (PrintStream)params.get("out");
		out.println("list");
		out.println("view 제품번호");
		out.println("add");
		out.println("delete 제품번호");
		out.println("update 제품번호");
		out.println();
	}


}









