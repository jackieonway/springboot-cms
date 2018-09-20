<!--Slider-->
    <div class="slider" id="about">
        <div class="callbacks_container">
            <ul class="rslides" id="slider">
                <#if headlines??>
                    <#list headlines as headline>
                        <li>
                            <a href="${headline.url}" target="_blank">
                                <div class="slider-img">
                                    <img src="${headline.picture}" style="height: 600px;" class="img-responsive" alt="${headline.alt}">
                                </div>
                            </a>
                            <#--<div class="slider-info">
                                <img src="skin/images/logo1.png" class="img-responsive" alt="logo">
                                <h3>Lorem ipsum dolor sit amet</h3>
                                <p>Lorem ipsum dolor amet, consectetur adipiscing elit.</p>
                            </div>-->
                        </li>
                    </#list>
                </#if>
                <#--<li>
                    <div class="slider-img">
                        <img src="skin/images/bann2.jpg" class="img-responsive" alt="Trade Zone">
                    </div>
                    <div class="slider-info">
                        <img src="skin/images/logo1.png" class="img-responsive" alt="logo">
                        <h3>Lorem ipsum dolor sit amet</h3>
                        <p>Lorem ipsum dolor amet, consectetur adipiscing elit.</p>
                    </div>
                </li>
                <li>
                    <div class="slider-img">
                        <img src="skin/images/bann1.jpg" class="img-responsive" alt="Trade Zone">
                    </div>
                    <div class="slider-info">
                        <img src="skin/images/logo1.png" class="img-responsive" alt="logo">
                        <h3>Quis nostrud exercitation </h3>
                        <p>Curabitur laoreet Nunc condimentum laoreet.</p>
                    </div>
                </li>
                <li>
                    <div class="slider-img">
                        <img src="skin/images/bann2.jpg" class="img-responsive" alt="Trade Zone">
                    </div>
                    <div class="slider-info">
                        <img src="skin/images/logo1.png" class="img-responsive" alt="logo">
                        <h3>Cupidatat non proident sunt</h3>
                        <p>Lorem ipsum dolor amet, consectetur adipiscing elit.</p>
                    </div>
                </li>
                <li>
                    <div class="slider-img">
                        <img src="skin/images/bann1.jpg" class="img-responsive" alt="Trade Zone">
                    </div>
                    <div class="slider-info">
                        <img src="skin/images/logo1.png" class="img-responsive" alt="logo">
                        <h3>Quis autem eum reprehenderit.</h3>
                        <p>Curabitur laoreet Nunc condimentum laoreet.</p>
                    </div>
                </li>-->

            </ul>

        </div>
        <div class="clearfix"></div>
    </div>
    <!-- //Modal1 -->
    <!--//Slider-->