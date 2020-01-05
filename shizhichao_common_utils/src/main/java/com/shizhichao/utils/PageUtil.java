package com.shizhichao.utils;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class PageUtil {

	private List list;//数据
	private long pageSize;//每页显示条数
	private long pageNum;//当前页数
	private long startIndex;//开始下标索引
	private long totalPage;//总页数
	private long totalRecords;//总记录数
	private long firstPage;//第一页
	private long lastPage;//最后一页
	private long previousPage;//上一页
	private long nextPage;//下一页
	private String url="";//需要分页的请求
	private String name="";//用于记录模糊条件
	
	//显示的页码
	private long startPage;
	private long endPage;
	
	/**
	 * @param pageNum    当前页
	 * @param totalRecords  总记录数  需要数据库查询
	 * @param pageSize   每页显示记录数
	 * @param request     将封装分页页面放置作用域
	 */
	public PageUtil(long pageNum,long totalRecords,long pageSize,HttpServletRequest request,String... like) {
		//this.name = name;
		this.pageSize = pageSize;
		this.pageNum = pageNum;
		this.totalRecords = totalRecords;
		this.firstPage=1;
		
		//需要求出开始下标索引
		startIndex = (pageNum -1)*pageSize;
		//需要计算出总页数
		totalPage = totalRecords%pageSize==0?totalRecords/pageSize:totalRecords/pageSize+1;
		
		
		if(totalPage<=9) {
			startPage=1;
			endPage=totalPage;
		}else {
			startPage=pageNum-4;
			endPage = pageNum+4;
			
			if(startPage < 1) {
				startPage=1;
				endPage = 9;
			}
			
			if(endPage > totalPage) {
				endPage = totalPage;
				startPage = totalPage-8;
			}
		}
		
		//上一页
		if(this.pageNum > 1) {
			this.previousPage = this.pageNum-1;
		}else {
			this.previousPage = this.firstPage;
		}
		
		this.lastPage=this.totalPage;
		//下一页
		if(this.pageNum < this.lastPage) {
			this.nextPage = this.pageNum+1;
		}else {
			this.nextPage = this.lastPage;
		}
/*		
		if(name == null) {
			name = "";
		}
*/		
		//String a = request.getParameter(name);
		
		
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String likename = "";
		for(int i=0;i<like.length;i++) {
			String name = request.getParameter(like[i]);
			if(name == null) {
				name="";
			}
			likename+="&"+like[i]+"="+name;
			request.setAttribute(like[i], name);
		}
		
		//-------------1.0分页版本----------------
		//page.jsp   拼接分页页面
		/*String str = "当前"+pageNum+"页/共"+totalPage+"页  总记录数"+totalRecords+
		"<a href='"+url+"?pageNum="+firstPage+name+"'>首页</a>"+
		"<a href='"+url+"?pageNum="+previousPage+name+"'>上一页</a>"+
		"<a href='"+url+"?pageNum="+nextPage+name+"'>下一页</a>"+
		"<a href='"+url+"?pageNum="+lastPage+name+"'>尾页</a>";*/
		
		//-------------2.0分页模糊版本----------------
		String str = "当前"+pageNum+"页/共"+totalPage+"页  总记录数"+totalRecords+
				"<a href='"+url+"?pageNum="+firstPage+likename+"'>首页</a>"+
				"<a href='"+url+"?pageNum="+previousPage+likename+"'>上一页</a>"+
				"<a href='"+url+"?pageNum="+nextPage+likename+"'>下一页</a>"+
				"<a href='"+url+"?pageNum="+lastPage+likename+"'>尾页</a>";
		
		request.setAttribute("fenye", str);
	}

	public PageUtil(Integer pageNum, Long totalRecords, Integer pageSize, HttpServletRequest request, List list,String... like) {
		//this.name = name;
				this.pageSize = pageSize;
				this.pageNum = pageNum;
				this.totalRecords = totalRecords;
				this.firstPage=1;
				
				//需要求出开始下标索引
				startIndex = (pageNum -1)*pageSize;
				//需要计算出总页数
				totalPage = totalRecords%pageSize==0?totalRecords/pageSize:totalRecords/pageSize+1;
				
				
				if(totalPage<=9) {
					startPage=1;
					endPage=totalPage;
				}else {
					startPage=pageNum-4;
					endPage = pageNum+4;
					
					if(startPage < 1) {
						startPage=1;
						endPage = 9;
					}
					
					if(endPage > totalPage) {
						endPage = totalPage;
						startPage = totalPage-8;
					}
				}
				
				//上一页
				if(this.pageNum > 1) {
					this.previousPage = this.pageNum-1;
				}else {
					this.previousPage = this.firstPage;
				}
				
				this.lastPage=this.totalPage;
				//下一页
				if(this.pageNum < this.lastPage) {
					this.nextPage = this.pageNum+1;
				}else {
					this.nextPage = this.lastPage;
				}
		/*		
				if(name == null) {
					name = "";
				}
		*/		
				//String a = request.getParameter(name);
				
				
				try {
					request.setCharacterEncoding("UTF-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				String likename = "";
				for(int i=0;i<like.length;i++) {
					String name = request.getParameter(like[i]);
					if(name == null) {
						name="";
					}
					likename+="&"+like[i]+"="+name;
					request.setAttribute(like[i], name);
				}
				
				//-------------1.0分页版本----------------
				//page.jsp   拼接分页页面
				/*String str = "当前"+pageNum+"页/共"+totalPage+"页  总记录数"+totalRecords+
				"<a href='"+url+"?pageNum="+firstPage+name+"'>首页</a>"+
				"<a href='"+url+"?pageNum="+previousPage+name+"'>上一页</a>"+
				"<a href='"+url+"?pageNum="+nextPage+name+"'>下一页</a>"+
				"<a href='"+url+"?pageNum="+lastPage+name+"'>尾页</a>";*/
				
				//-------------2.0分页模糊版本----------------
				String str = "当前"+pageNum+"页/共"+totalPage+"页  总记录数"+totalRecords+
						"<a href='"+url+"?pageNum="+firstPage+likename+"'>首页</a>"+
						"<a href='"+url+"?pageNum="+previousPage+likename+"'>上一页</a>"+
						"<a href='"+url+"?pageNum="+nextPage+likename+"'>下一页</a>"+
						"<a href='"+url+"?pageNum="+lastPage+likename+"'>尾页</a>";
				
				request.setAttribute("fenye", str);
				request.setAttribute("list", list);
			}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public long getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(long firstPage) {
		this.firstPage = firstPage;
	}

	public long getLastPage() {
		return lastPage;
	}

	public void setLastPage(long lastPage) {
		this.lastPage = lastPage;
	}

	public long getPreviousPage() {
		return previousPage;
	}

	
	public void setName(String name,HttpServletRequest request ) {
		this.name = name;
		String str = "当前"+pageNum+"页/共"+totalPage+"页  总记录数"+totalRecords+
				"<a href='"+url+"?pageNum="+firstPage+name+"'>首页</a>"+
				"<a href='"+url+"?pageNum="+previousPage+name+"'>上一页</a>"+
				"<a href='"+url+"?pageNum="+nextPage+name+"'>下一页</a>"+
				"<a href='"+url+"?pageNum="+lastPage+name+"'>尾页</a>";
				
				request.setAttribute("fenye", str);
	}

	public void setPreviousPage(long previousPage) {
		this.previousPage = previousPage;
	}

	public long getNextPage() {
		return nextPage;
	}

	public void setNextPage(long nextPage) {
		this.nextPage = nextPage;
	}

	public long getStartPage() {
		return startPage;
	}

	public void setStartPage(long startPage) {
		this.startPage = startPage;
	}

	public long getEndPage() {
		return endPage;
	}

	public void setEndPage(long endPage) {
		this.endPage = endPage;
	}

	public String getName() {
		return name;
	}
	public List getList() {
		return list;
	}

	public void setList(List list) {
		this.list = list;
	}

	public long getPageSize() {
		return pageSize;
	}

	public void setPageSize(long pageSize) {
		this.pageSize = pageSize;
	}

	public long getPageNum() {
		return pageNum;
	}

	public void setPageNum(long pageNum) {
		this.pageNum = pageNum;
	}

	public long getStartIndex() {
		return startIndex;
	}

	public void setStartIndex(long startIndex) {
		this.startIndex = startIndex;
	}

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}
	
	
	
}
