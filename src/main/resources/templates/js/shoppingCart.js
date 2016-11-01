$(document).ready(function() {
  $(".search").keyup(function () {
    var searchTerm = $(".search").val();
    var listItem = $('.results tbody').children('tr');
    var searchSplit = searchTerm.replace(/ /g, "'):containsi('")

  $.extend($.expr[':'], {'containsi': function(elem, i, match, array){
        return (elem.textContent || elem.innerText || '').toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
    }
  });

  $(".results tbody tr").not(":containsi('" + searchSplit + "')").each(function(e){
    $(this).attr('visible','false');
  });

  $(".results tbody tr:containsi('" + searchSplit + "')").each(function(e){
    $(this).attr('visible','true');
  });

  var jobCount = $('.results tbody tr[visible="true"]').length;
  $('.counter').text("Znaleziono "+jobCount);

  if(jobCount == '0')
  {
        $('.no-result').show();
  }
  else
  {
        $('.no-result').hide();}
  });
});

function update(id) {
    fieldName = "quant["+id+"]";
    var quantity = $("input[name='"+fieldName+"']").val();

    $.ajax({
        url: '/cart/update',
        type: 'post',
        dataType: 'json',
        data: "perfumeId="+id+"&quantity="+quantity,
        success: function (data) {
            showModal("Zapisano", "Nowa ilość produktu: "+quantity);
        },
        error: function (jqXHR, textStatus, errorThrown)
        {
            showModal("Error", textStatus);
        }
    });
}

function remove(id) {
    bootbox.confirm("Czy na pewno chcesz usunąć tą pozycję?",
        function(result){
        if(result==true){
            $.ajax({
                url: '/cart/delete?perfumeId='+id,
                type: 'delete',
                success: function (data) {
                    bootbox.alert("Usunięto",
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

function clearCart() {
    bootbox.confirm("Czy na pewno chcesz usunąć wszystkie produkty z koszyka?",
        function(result){
        if(result==true){
            $.ajax({
                url: '/cart/clear',
                type: 'delete',
                success: function (data) {
                    location.reload();
                },
                error: function (jqXHR, textStatus, errorThrown)
                {
                    showModal("Error", errorThrown);
                }
            });
        }
    });
}

function order() {
$("#orderForm").validate({
  errorClass: "has-error",
  errorElement: "span",
  rules: {
    firstName: {
      required: true
    },
    lastName: {
      required: true
    }
  },
  messages: {
    firstName: {
      required: "Proszę podać imię"
    },
    lastName: {
      required: "Proszę podać nazwisko"
    }
  },
  highlight: function (element, required) {
      $(element).fadeOut(function () {
          $(element).fadeIn();
          $(element).css('border', '2px solid #f74a4e');
      });
  },
  unhighlight: function (element, errorClass, validClass) {
      $(element).css('border', '1px solid #CCC');
  },
  submitHandler: function(form) {
    var customer = {
                firstName: $("#firstName").val(),
                lastName: $("#lastName").val()
    }

    $.ajax({
        url: '/admin/order',
        type: 'post',
        dataType: 'json',
        data: customer,
        success: function (data) {
            bootbox.alert("Zamówiono",
            function(result){
                location.reload();
            });
        },
        error: function (jqXHR, textStatus, errorThrown)
        {
            showModal("Error", textStatus);
        }
    });
  },
  invalidHandler: function(event, validator) {
      // 'this' refers to the form
      var errors = validator.numberOfInvalids();
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