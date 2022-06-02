function getCart(callback) {
	$.ajax({
		type : "GET",
		dataType : "json",
		url : serviceEndpointURL + "/api/cart",
		success : function(data) {
			callback(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR, textStatus, errorThrown);
		}
	});
}

function deleteCart(cartId, callback) {
	$.ajax({
		type : "DELETE",
		headers : {
			"X-XSRF-TOKEN" : getCookie("XSRF-TOKEN")
		},
		url : serviceEndpointURL + "/api/cart/" + cartId,
		success : function(data) {
			callback(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR, textStatus, errorThrown);
		}
	});
}