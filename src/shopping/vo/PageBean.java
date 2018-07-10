package shopping.vo;

import java.util.List;
/**
 * 
 * @author lijian
 *
 * @param <T> 泛型 T为 分页记录类型， 例如商品记录， 订单记录
 */
public class PageBean<T> { 
		private String categoryName;
		private Integer currPage; //当前页
		private Integer pageSize;//总页数
		private Integer totalCount;//当前分类的总数
		private Integer totalPage;//总页数
		private List<T> list;// 查询记录保存的集合
		
		
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		public Integer getCurrPage() {
			return currPage;
		}
		public void setCurrPage(Integer currPage) {
			this.currPage = currPage;
		}
		public Integer getPageSize() {
			return pageSize;
		}
		public void setPageSize(Integer pageSize) {
			this.pageSize = pageSize;
		}
		public Integer getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(Integer totalCount) {
			this.totalCount = totalCount;
		}
		public Integer getTotalPage() {
			return totalPage;
		}
		public void setTotalPage(Integer totalPage) {
			this.totalPage = totalPage;
		}
		public List<T> getList() {
			return list;
		}
		public void setList(List<T> list) {
			this.list = list;
		}
		
		
	}

