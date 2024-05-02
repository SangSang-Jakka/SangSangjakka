$('document').ready(function(){
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
            emptyTable: "데이터 없음",
            info: "현재 _START_-_END_ of _TOTAL_ 건",
            infoEmpty: "데이터 없음",
            lengthMenu: "페이지당 _MENU_ 개씩 보기",
            infoFiltered: "( _MAX_건의 데이터에서 필터링됨 )",
            search: "에서 검색: ",
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

    // column별 검색 기능 추가
    $('#myTable_filter').prepend('<select id="select"></select>');
    $('#myTable > thead > tr').children().each(function (indexInArray, valueOfElement) { 
        if (valueOfElement.className !== 'datatable-nosort') {
            $('#select').append('<option>'+valueOfElement.innerHTML+'</option>');
        }
    });
    
    $('.dataTables_filter input').unbind().bind('keyup', function () {
        var colIndex = document.querySelector('#select').selectedIndex;
        dataTable.column(colIndex).search(this.value).draw();
    });

    // 날짜검색 이벤트 리바인딩
    $('#myTable_filter').prepend('<input type="date" id="toDate" placeholder="yyyy-MM-dd"> ');
    $('#myTable_filter').prepend('<input type="date" id="fromDate" placeholder="yyyy-MM-dd">~');
    
    // 날짜 검색
    $('#toDate, #fromDate').unbind().bind('keyup',function(){
        var fromDate = $('#fromDate').val();
        var toDate = $('#toDate').val();

        // 필요한 형식에 따라 날짜 파싱
        var fromDateParsed = moment(fromDate, 'YYYY-MM-DD', true);
        var toDateParsed = moment(toDate, 'YYYY-MM-DD', true);

        // 날짜 유효성 검사
        if (fromDateParsed.isValid() && toDateParsed.isValid()) {
            // 검색 범위 지정
            $.fn.dataTable.ext.search.push(function(settings, data, dataIndex) {
                var targetDate = moment(data[3], 'YYYY-MM-DD', true);
                if (targetDate.isValid()) {
                    return targetDate.isBetween(fromDateParsed, toDateParsed, null, '[]');
                }
                return false;
            });
            dataTable.draw();
        }
    });

    // 셀렉트 목록 숨기기
    $('#select option:contains("번호")').remove();
    $('#select option:contains("Action")').remove();
});