<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">


.preview
{
    padding: 10px;
    position: relative;
}

.preview i
{
    color: white;
    font-size: 35px;
    transform: translate(50px,130px);
}

.preview-img
{
    border-radius: 100%;
    box-shadow: 0px 0px 5px 2px rgba(0,0,0,0.7);
}

.browse-button
{
    width: 200px;
    height: 200px;
    border-radius: 100%;
    position: absolute; /* Tweak the position property if the element seems to be unfit */
    top: 10px;
    left: 132px;
    background: linear-gradient(180deg, transparent, black);
    opacity: 0;
    transition: 0.3s ease;
}

.browse-button:hover
{
    opacity: 1;
}

.browse-input
{
    width: 200px;
    height: 200px;
    border-radius: 100%;
    transform: translate(-1px,-26px);
    opacity: 0;
}

.form-group
{
    width:  250px;
    margin: 10px auto;
}

.form-group input
{
    transition: 0.3s linear;
}

.form-group input:focus
{
    border: 1px solid crimson;
    box-shadow: 0 0 0 0;
}

.Error
{
    color: crimson;
    font-size: 13px;
}

.Back
{
    font-size: 25px;
}

</style>

</head>
<body>

	<form action="" method="post">
            <div class="preview text-center">
                <img class="preview-img" src="http://simpleicon.com/wp-content/uploads/account.png" alt="Preview Image" width="200" height="200" id= 'image_section'/>
                <div class="browse-button">
                    <i class="fa fa-pencil-alt"></i>
                    <input class="browse-input" type="file" required name="UploadedFile" id="imgInput"/>
                </div>
                <span class="Error"></span>
            </div>
          
        </form>
    

</body>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
function readURL(input) {
	 if (input.files && input.files[0]) {
	  var reader = new FileReader();
	  
	  reader.onload = function (e) {
	   $('#image_section').attr('src', e.target.result);  
	  }
	  
	  reader.readAsDataURL(input.files[0]);
	  }
	}
	  
	$("#imgInput").change(function(){
	   readURL(this);
	});
</script>
</html>