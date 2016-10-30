//This will return a given query string parameter from the current url
function getParameterByName(name) {
    var match = RegExp('[?&]' + name + '=([^&]*)').exec(window.location.search);
    return match && decodeURIComponent(match[1].replace(/\+/g, ' '));
}


$(document).ready(function() {
    var columnsSize = 7;
    var groupByColumn = getParameterByName('column');
    var ccc = (parseInt(groupByColumn)+1);
    if(groupByColumn==null || (parseInt(groupByColumn)+1) > columnsSize || parseInt(groupByColumn) < 0 ){
      groupByColumn = 0;
    }

    var table = $('#orders').DataTable({
        "columnDefs": [
            { "visible": false, "targets": groupByColumn }
        ],
        "columnDefs": [
            { "targets": columnsSize - 1, "orderable": false }
        ],
        "order": [[ groupByColumn, 'asc' ]],
        "displayLength": 25,
        "drawCallback": function ( settings ) {
            var api = this.api();
            var rows = api.rows( {page:'current'} ).nodes();
            var last=null;

            api.column(groupByColumn, {page:'current'} ).data().each( function ( group, i ) {
                if ( last !== group ) {
                    $(rows).eq( i ).before(
                        '<tr class="group"><td colspan="'+columnsSize+'">'+group+'</td></tr>'
                    );

                    last = group;
                }
            } );
        }
    } );

    // Order by the grouping
    $('#orders tbody').on( 'click', 'tr.group', function () {
        var currentOrder = table.order()[0];
        if ( currentOrder[0] === groupByColumn && currentOrder[1] === 'asc' ) {
            table.order( [ groupByColumn, 'desc' ] ).draw();
        }
        else {
            table.order( [ groupByColumn, 'asc' ] ).draw();
        }
    } );
} );

function removeOrderDetail(headerId, detailId) {
    bootbox.confirm("Czy na pewno chcesz usunąć tą pozycję?",
        function(result){
        if(result==true){
            $.ajax({
                url: '/admin/order/detail/delete?orderHeaderId='+headerId+'&orderDetailId='+detailId,
                type: 'delete',
                success: function (data) {
                                    bootbox.confirm("Usunięto",
                                    function(result){
                                        location.reload();
                                    });
                                },
                error: function (jqXHR, textStatus, errorThrown)
                {
                    showModal("Error", errorThrown);
                }
            });
        }
    });
}

function removeAllOrders() {
    bootbox.confirm("Czy na pewno chcesz usunąć wszystkie zamówienia?",
        function(result){
        if(result==true){
            $.ajax({
                url: '/admin/order/delete/all',
                type: 'delete',
                success: function (data) {
                                    bootbox.confirm("Usunięto",
                                    function(result){
                                        location.reload();
                                    });
                                },
                error: function (jqXHR, textStatus, errorThrown)
                {
                    showModal("Error", errorThrown);
                }
            });
        }
    });
}

function showModal(header, body_text){
    $("#modal-title-text").text(header);
    $("#modal-body-text").text(body_text);
    $("#myModal").modal('show');
}

function search(category){
    var searchPerfume = $("#searchField").val();
    window.location.href = "/perfumes/"+category+"/"+searchPerfume;
}