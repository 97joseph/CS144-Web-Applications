<% String URL = "https://" + request.getServerName()+":8443"+request.getContextPath()+"/confirmation"; %>
<html>
    <head> 
        <title>Payment input</title>
       
        <script type="text/javascript">

            function check()
            {
                if (!payment.creditCardNum.value)
                {
                    alert ("Please Enter a Credit card number ");
                    return (false);
                }
                var value=payment.creditCardNum.value;
                if (/[^0-9-\s]+/.test(value))
                {
                    alert ("Enter a valid credit card number");
                    return false;
                }

                 var nCheck = 0, nDigit = 0, bEven = false;
                    value = value.replace(/\D/g, "");

                for (var n = value.length - 1; n >= 0; n--) {
                  var cDigit = value.charAt(n),
                    nDigit = parseInt(cDigit, 10);
                    if (bEven) {
                    if ((nDigit *= 2) > 9) nDigit -= 9;
                    }

                  nCheck += nDigit;
                  bEven = !bEven;
                }
                if(!((nCheck % 10) == 0))
                {
                    alert ("Enter a valid credit card number");
                    return false;
                }
                return (true);
            }
            // takes the form field value and returns true on valid number
            function valid_credit_card(value) {
                // accept only digits, dashes or spaces
                if (/[^0-9-\s]+/.test(value)) return false;

                // The Luhn Algorithm. It's so pretty.
                var nCheck = 0, nDigit = 0, bEven = false;
                value = value.replace(/\D/g, "");

                for (var n = value.length - 1; n >= 0; n--) {
                  var cDigit = value.charAt(n),
                    nDigit = parseInt(cDigit, 10);

                if (bEven) {
                    if ((nDigit *= 2) > 9) nDigit -= 9;
                    }

                  nCheck += nDigit;
                  bEven = !bEven;
                }

            return (nCheck % 10) == 0;
            }

        </script>
    
    </head>
	<body>
        <h2>Enter credit card information and Proceed to checkout</h2>
        <form name="payment" method="POST" action="<%= URL %>">
            <ul>
                <li>Item ID: <%= request.getAttribute("itemID") %></li>
                <li>Name: <%= request.getAttribute("itemName") %></li>
                <li>Buy Price: <%= request.getAttribute("buyPrice") %></li>      
            </ul>
            <p>Enter Credit Card no.:
                <input type="text" name="creditCardNum"/>
                <input type="submit" value="Submit" onclick="return check();" /> 
            </p>
        </form>
	</body>
</html>