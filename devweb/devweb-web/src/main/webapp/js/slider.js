PrimeFaces.widget.Slider = PrimeFaces.widget.BaseWidget.extend({
    init: function(b) {
    	createspantag(b);
        this._super(b);
        this.cfg.displayTemplate = this.cfg.displayTemplate || (this.cfg.range ? "{min} - {max}" : "{value}");
        if (this.cfg.range) {
            var a = this.cfg.input.split(",");
            this.input = $(PrimeFaces.escapeClientId(a[0]) + "," + PrimeFaces.escapeClientId(a[1]))
        } else {
            this.input = $(PrimeFaces.escapeClientId(this.cfg.input))
        }
        if (this.cfg.display) {
            this.output = $(PrimeFaces.escapeClientId(this.cfg.display))
        }
        this.jq.slider(this.cfg);
        this.bindEvents();
        var sldr = this.jq.attr("id");
        
    },
    bindEvents: function() {
        var a = this;
        this.jq.bind("slide", function(b, c) {
            a.onSlide(b, c)
        });
        if (this.cfg.onSlideStart) {
            this.jq.bind("slidestart", function(b, c) {
                a.cfg.onSlideStart.call(this, b, c)
            })
        }
        this.jq.bind("slidestop", function(b, c) {
            a.onSlideEnd(b, c)
        });
        this.input.on("keydown.slider", function(f) {
            var d = $.ui.keyCode,
                c = f.which;
            switch (c) {
                case d.UP:
                case d.DOWN:
                case d.LEFT:
                case d.RIGHT:
                case d.BACKSPACE:
                case d.DELETE:
                case d.END:
                case d.HOME:
                case d.TAB:
                    break;
                default:
                    var g = f.metaKey || f.ctrlKey,
                        b = (c >= 48 && c <= 57) || (c >= 96 && c <= 105);
                    if (f.altKey || (f.shiftKey && !(c === d.UP || c === d.DOWN || c === d.LEFT || c === d.RIGHT))) {
                        f.preventDefault()
                    }
                    if (!b && !g) {
                        f.preventDefault()
                    }
                    break
            }
        }).on("keyup.slider", function(b) {
            a.setValue(a.input.val())
        })
    },
    onSlide: function(a, b) {
    	changeSliderColor(a);
    	$('div.slider-fill').css('background','green');
        if (this.cfg.onSlide) {
            this.cfg.onSlide.call(this, a, b)
        }
        if (this.cfg.range) {
            this.input.eq(0).val(b.values[0]);
            this.input.eq(1).val(b.values[1]);
            if (this.output) {
                this.output.html(this.cfg.displayTemplate.replace("{min}", b.values[0]).replace("{max}", b.values[1]))
            }
        } else {
            this.input.val(b.value);
            if (this.output) {
                this.output.html(this.cfg.displayTemplate.replace("{value}", b.value))
            }
        }
    },
    onSlideEnd: function(c, d) {
        if (this.cfg.onSlideEnd) {
            this.cfg.onSlideEnd.call(this, c, d)
        }
        if (this.cfg.behaviors) {
            var a = this.cfg.behaviors.slideEnd;
            if (a) {
                var b = {
                    params: [{
                        name: this.id + "_slideValue",
                        value: d.value
                    }]
                };
                a.call(this, b)
            }
        }changeSliderColor(event);
    },
    getValue: function() {
        return this.jq.slider("value")
    },
    setValue: function(a) {
        this.jq.slider("value", a)
    },
    getValues: function() {
        return this.jq.slider("values")
    },
    setValues: function(a) {
        this.jq.slider("values", a)
    },
    enable: function() {
        this.jq.slider("enable")
    },
    disable: function() {
        this.jq.slider("disable")
    }
});

function createspantag(event){
	var divtag = document.getElementById('slider1');
	var span = document.createElement('span');
	span.setAttribute('class', 'slider-fill');
	
	span.setAttribute('style', 'width: 14%;"');
//	document.body.appendChild(div);
	divtag.appendChild(span);
}

function changeSliderColor(event){
	var divtag = document.getElementById('slider1');
	var val1 = divtag.childNodes[0].style.width;
	var val2 = divtag.childNodes[1].style.left
	var finalvar ='width:'+val2+';';
	divtag.childNodes[0].setAttribute("style", finalvar);
	//var el = event.target;
}
