$('document').ready(function() {

	// 초기 DataTables 설정
	var dataTable = $('.data-table').DataTable({
		scrollCollapse: true,
		autoWidth: false,
		responsive: true,
		columnDefs: [{
			targets: "datatable-nosort",
			orderable: false,
		}],
		lengthMenu: [[10, 25, 50, -1], [10, 25, 50, "All"]],
		language: {
			emptyTable: "게시물이 없습니다.",
			info: "현재 _START_-_END_ of _TOTAL_ 건",
			infoEmpty: "데이터 없음",
			lengthMenu: "페이지당 _MENU_ 개씩 보기",
			infoFiltered: "( _MAX_건의 데이터에서 필터링됨 )",
			/* search: "검색어: ", */
			zeroRecords: "일치하는 데이터 없음",
			loadingRecords: "로딩중",
			processing: "잠시만 기다려주세요.",
			searchPlaceholder: "검색어를 입력해주세요",
			paginate: {
				next: '<i class="ion-chevron-right"></i>',
				previous: '<i class="ion-chevron-left"></i>'
			}
		}
	});


	// 커스텀 검색 기능을 추가하여 날짜 범위에 따라 필터링
	$.fn.dataTable.ext.search.push(function(settings, data, dataIndex) {
		var minDate = $('#min').val() ? new Date($('#min').val()) : null;
		var maxDate = $('#max').val() ? new Date($('#max').val()) : null;
		var rowDate = new Date(data[2].substring(0, 10)); // 작성일에서 날짜 부분만 추출

		if ((minDate === null && maxDate === null) ||
			(minDate === null && rowDate <= maxDate) ||
			(minDate <= rowDate && maxDate === null) ||
			(minDate <= rowDate && rowDate <= maxDate)) {
			return true;
		}
		return false;
	});


	// 날짜 필터링 기능 추가
	$('#min, #max').change(function() {
		dataTable.draw();
	});


	// 조회 조건 변경 시 DataTables 다시 그리기
	$('#conditionSelect').change(function() {
		var selectedCondition = $(this).val();
		// AJAX를 통해 선택한 조회 조건에 해당하는 데이터를 서버로부터 가져옴
		$.ajax({
			url: '/sangsangjakka/admin/dashboard/comment/manage.do',
			type: 'POST',
			data: { condition: selectedCondition }, // 선택한 조회 조건 전달
			success: function(data) {
				var newData = [];

				// 성공시
				data.forEach(function(item) {
					var row = [

						item.cmntSeq,
						item.userNick,
						item.cmntRegdate,
						item.cmntContents,
						item.cmntReportCnt,
						// Action 열 추가
						'<div class="dropdown">' +
						'<a class="btn btn-link font-24 p-0 line-height-1 no-arrow dropdown-toggle" href="#" role="button" data-toggle="dropdown">' +
						'<i class="dw dw-more"></i>' +
						'</a>' +
						'<div class="dropdown-menu dropdown-menu-right dropdown-menu-icon-list">' +
						'<a class="dropdown-item" href="#"><i class="dw dw-eye"></i> View</a>' +
						'<a class="dropdown-item" href="#"><i class="dw dw-edit2"></i> Edit</a>' +
						'<a class="dropdown-item" href="#"><i class="dw dw-delete-3"></i> Delete</a>' +
						'</div>' +
						'</div>'

					];

					newData.push(row);
				});

				// 성공적으로 데이터를 받았을 떄 다시 그리기
				dataTable.clear().rows.add(newData).draw();
			},
			error: function(xhr, status, error) {
				// 에러 처리
			}
		});
	});

});