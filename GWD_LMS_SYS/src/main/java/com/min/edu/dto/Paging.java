package com.min.edu.dto;

//게시글의 개수와 그룹
//화면에서 입력 받은 index 번호
public class Paging {

	private int page;//현재 선택된 페이지 0
	private int countList;//페이지당 몇 개의 게시글을 보여 줄건지 1(8개) 2(8개) 3(1개)
	private int totalCount;//총 게시글의 개수 17개
	
	private int countPage;//화면에 몇 개의 페이지를 보여 줄건지 - 123- 3개
	private int totalPage;// -123- -456- -7- 7개
	private int startPage; //현재 화면의 시작 페이지번호 1 /4 /7
	private int endPage;//현재 화면의 끝 페이지 3 /6 /7
	
	public Paging() {
	}

	@Override
	public String toString() {
		return "Paging [page=" + page + ", countList=" + countList + ", totalCount=" + totalCount + ", countPage="
				+ countPage + ", totalPage=" + totalPage + ", startPage=" + startPage + ", endPage=" + endPage + "]";
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
				if (totalPage < page) {
					page=totalPage;
				}
		this.page = page;
	}

	public int getCountList() {
		return countList;
	}

	public void setCountList(int countList) {
		this.countList = countList;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCountPage() {
		return countPage;
	}

	public void setCountPage(int countPage) {
		this.countPage = countPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		
		int totalPageResult = totalCount/countList;
		if (totalCount%countList>0) {//나머지가 있다 -> 페이지가 하나 더 있어야 한다
			totalPageResult++;
		}
		this.totalPage = totalPageResult;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		
		int startPageResult = ((page-1)/countPage)*countPage+1;
							
		this.startPage = startPageResult;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		int endPageResult = startPage+countPage-1; //1
		if (endPageResult>totalPage) {
			endPageResult = totalPage;
		}
		this.endPage = endPageResult;
	}
	
}
