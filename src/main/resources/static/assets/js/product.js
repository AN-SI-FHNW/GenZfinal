function postProduct(product, callbackSuccess, callbackError) {
	$.ajax({
		type : "POST",
		contentType : "application/json",
		headers : {
			"X-XSRF-TOKEN" : getCookie("XSRF-TOKEN")
		},
		url : serviceEndpointURL + "/api/product", // DEFINE AFTER +
		data : product,
		success : function(data) {
			callbackSuccess(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR, textStatus, errorThrown);
			callbackError(jqXHR.responseJSON.message);
		}
	});
}

function getProduct(productID, callback) {
	$.ajax({
		type : "GET",
		dataType : "json",
		url : serviceEndpointURL + "/api/product/" + productID,
		success : function(data) {
			callback(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR, textStatus, errorThrown);
		}
	});
}

function getProducts(callback) {
	$.ajax({
		type : "GET",
		dataType : "json",
		url : serviceEndpointURL + "/api/product",
		success : function(data) {
			callback(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR, textStatus, errorThrown);
		}
	});
}

function putProduct(productID, product, callbackSuccess, callbackError) {
	$.ajax({
		type : "PUT",
		contentType : "application/json",
		headers : {
			"X-XSRF-TOKEN" : getCookie("XSRF-TOKEN")
		},
		url : serviceEndpointURL + "/api/product/" + productID,
		data : product,
		success : function(data) {
			callbackSuccess(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR, textStatus, errorThrown);
			callbackError(jqXHR.responseJSON.message);
		}
	});
}

function deleteProduct(productID, callback) {
	$.ajax({
		type : "DELETE",
		headers : {
			"X-XSRF-TOKEN" : getCookie("XSRF-TOKEN")
		},
		url : serviceEndpointURL + "/api/product/" + productID,
		success : function(data) {
			callback(data);
		},
		error : function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR, textStatus, errorThrown);
		}
	});
}

function getProductJSON(id, name, maxNoOfProducts, minNrOfPalletSpaces) {
	if (id === null) {
		return JSON.stringify({
			"name" : name,
			"maxNoOfProducts" : maxNoOfProducts,
			"minNrOfPalletSpaces" : minNrOfPalletSpaces,
		});
	}
	return JSON.stringify({
		"id" : id,
		"name" : name,
		"maxNoOfProducts" : maxNoOfProducts,
		"minNrOfPalletSpaces" : minNrOfPalletSpaces,
	});
}