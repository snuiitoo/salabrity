package cart.controller;
//ㅇㅇㅇ22
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cart.model.service.CartService;
import member.model.dto.Member;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/order/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	CartService cartService = new CartService();
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			// 업무로직
			HttpSession session = request.getSession();
			Member loginMember = (Member) session.getAttribute("loginMember");
			String memberId = loginMember.getMemberId();
			
			//아이디로 장바구니 조회
			Map<String, Object> map = cartService.findByIdCart(memberId);
			System.out.println("서블렛@"+map);
			// view단 처리
			request.setAttribute("map", map);
			request.getRequestDispatcher("/WEB-INF/views/order/cart.jsp").forward(request, response);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	
	}

}
