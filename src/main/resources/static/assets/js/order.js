function postOrder(order, callbackSuccess, callbackError) {
	$.ajax({
		type : "POST",
		contentType : "application/json",
		headers : {
			"X-XSRF-TOKEN" : getCookie("XSRF-TOKEN")
		},
		url : serviceEndpointURL + "/product/order", // DEFINE AFTER +
		data : order,
		success : function(data) {
			callbackSuccess(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR, textStatus, errorThrown);
			callbackError(jqXHR.responseJSON.message);
		}
	});
}

function getOrders(callback) {
	$.ajax({
		type : "GET",
		dataType : "json",
		url : serviceEndpointURL + "/api/order",
		success : function(data) {
			callback(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR, textStatus, errorThrown);
		}
	});
}

function getOrderJSON(id, orderQuantity, customerList) {
    return JSON.stringify({
        "productId": id,
    	"orderQuantity": orderQuantity,
        "customerId": customerList
    });
}