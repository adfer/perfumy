function service_available(available) {
    var message;
    if(available==true){
        message = "Czy na pewno chcesz odblokować serwis?"
    }
    else{
        message = "Czy na pewno chcesz zablokować serwis?";
    }
    bootbox.confirm(message,
        function(result){
            if(result==true){
                $.ajax({
                    url: '/admin/settings/available',
                    type: 'post',
                    data: "available="+available,
                    success: function (data) {
                        if(data==true) {
                            bootbox.alert("Serwis został odblokowany",
                                function (result) {
                                    location.reload();
                                });
                        }
                        else{
                            bootbox.alert("Serwis został zablokowany",
                                function (result) {
                                    location.reload();
                                });
                        }
                    },
                    error: function (jqXHR, textStatus, errorThrown)
                    {
                        showModal("Error", errorThrown);
                    }
                });
            }
        });
}