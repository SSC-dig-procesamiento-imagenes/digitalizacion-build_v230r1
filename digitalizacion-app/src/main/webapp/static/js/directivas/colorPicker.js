"use strict";
var colorPicker = angular.module("colorpicker", []).factory("ColorHelper", function() { return { hsla2hsva: function(e) { var t = Math.min(e.h, 1),
                o = Math.min(e.s, 1),
                r = Math.min(e.l, 1),
                a = Math.min(e.a, 1); if (0 === r) return { h: t, s: 0, v: 0, a: a }; var n = r + o * (1 - Math.abs(2 * r - 1)) / 2; return { h: t, s: 2 * (n - r) / n, v: n, a: a } }, hsva2hsla: function(e) { var t = e.h,
                o = e.s,
                r = e.v,
                a = e.a; if (0 === r) return { h: t, s: 0, l: 0, a: a }; if (0 === o && 1 === r) return { h: t, s: 1, l: 1, a: a }; var n = r * (2 - o) / 2; return { h: t, s: r * o / (1 - Math.abs(2 * n - 1)), l: n, a: a } }, rgbaToHsva: function(e) { var t, o, r = Math.min(e.r, 1),
                a = Math.min(e.g, 1),
                n = Math.min(e.b, 1),
                s = Math.min(e.a, 1),
                i = Math.max(r, a, n),
                l = Math.min(r, a, n),
                c = i,
                u = i - l; if (o = 0 === i ? 0 : u / i, i === l) t = 0;
            else { switch (i) {
                    case r:
                        t = (a - n) / u + (n > a ? 6 : 0); break;
                    case a:
                        t = (n - r) / u + 2; break;
                    case n:
                        t = (r - a) / u + 4 }
                t /= 6 } return { h: t, s: o, v: c, a: s } }, hsvaToRgba: function(e) { var t, o, r, a = e.h,
                n = e.s,
                s = e.v,
                i = e.a,
                l = Math.floor(6 * a),
                c = 6 * a - l,
                u = s * (1 - n),
                p = s * (1 - c * n),
                h = s * (1 - (1 - c) * n); switch (l % 6) {
                case 0:
                    t = s, o = h, r = u; break;
                case 1:
                    t = p, o = s, r = u; break;
                case 2:
                    t = u, o = s, r = h; break;
                case 3:
                    t = u, o = p, r = s; break;
                case 4:
                    t = h, o = u, r = s; break;
                case 5:
                    t = s, o = u, r = p } return { r: t, g: o, b: r, a: i } }, stringToHsva: function(e) { var t = [{ re: /(rgb)a?\(\s*(\d{1,3})\s*,\s*(\d{1,3})\s*%?,\s*(\d{1,3})\s*%?(?:,\s*(\d+(?:\.\d+)?)\s*)?\)/, parse: function(e) { return ["rgb", parseInt(e[2]), parseInt(e[3]), parseInt(e[4]), isNaN(parseFloat(e[5])) ? 1 : parseFloat(e[5])] } }, { re: /(hsl)a?\(\s*(\d{1,3})\s*,\s*(\d{1,3})%\s*,\s*(\d{1,3})%\s*(?:,\s*(\d+(?:\.\d+)?)\s*)?\)/, parse: function(e) { return ["hsl", parseInt(e[2]), parseInt(e[3]), parseInt(e[4]), isNaN(parseFloat(e[5])) ? 1 : parseFloat(e[5])] } }, { re: /#([a-fA-F0-9]{2})([a-fA-F0-9]{2})([a-fA-F0-9]{2})$/, parse: function(e) { return ["hex", parseInt(e[1], 16), parseInt(e[2], 16), parseInt(e[3], 16), 1] } }, { re: /#([a-fA-F0-9])([a-fA-F0-9])([a-fA-F0-9])$/, parse: function(e) { return ["hex", parseInt(e[1] + e[1], 16), parseInt(e[2] + e[2], 16), parseInt(e[3] + e[3], 16), 1] } }];
            e = e.toLowerCase(); var o = null; for (var r in t)
                if (t.hasOwnProperty(r)) { var a = t[r],
                        n = a.re.exec(e),
                        s = n && a.parse(n); if (s) return o = "hex" === s[0] || "rgb" === s[0] ? this.rgbaToHsva({ r: s[1] / 255, g: s[2] / 255, b: s[3] / 255, a: s[4] }) : this.hsla2hsva({ h: s[1] / 360, s: s[2] / 100, l: s[3] / 100, a: s[4] }) }
            return o } } });
colorPicker.directive("colorPicker", ["$document", "$compile", "ColorHelper", function(e, t, o) { return { restrict: "A", scope: { colorPickerModel: "=", colorPickerOutputFormat: "=" }, controller: ["$scope", function(e) {
            function t(e) { return { r: Math.round(255 * e.r), g: Math.round(255 * e.g), b: Math.round(255 * e.b), a: e.a } }
            e.show = !1, e.sAndLMax = {}, e.hueMax = {}, e.alphaMax = {}, e.type = 0, e.hsva = { h: .5, s: 1, v: 1, a: 1 }, e.hueSlider = { left: 0 }, e.sAndLSlider = { left: 0, top: 0 }, e.alphaSlider = { left: 0 }, e.rbgaSteps = { r: 1, g: 1, b: 1, a: .1 }, e.hslaSteps = { h: 1, s: 1, l: 1, a: .1 }, e.cancelButtonClass = "", e.showCancelButton = !1, e.extraLargeClass = "", "rgba" === e.colorPickerOutputFormat ? e.type = 1 : "hsla" === e.colorPickerOutputFormat && (e.type = 2), e.setSaturation = function(t, r) { var a = o.hsva2hsla(e.hsva);
                a.s = t / r, e.hsva = o.hsla2hsva(a) }, e.setLightness = function(t, r) { var a = o.hsva2hsla(e.hsva);
                a.l = t / r, e.hsva = o.hsla2hsva(a) }, e.setHue = function(t, o) { e.hsva.h = t / o }, e.setAlpha = function(t, o) { e.hsva.a = t / o }, e.setR = function(t, r) { var a = o.hsvaToRgba(e.hsva);
                a.r = t / r, e.hsva = o.rgbaToHsva(a) }, e.setG = function(t, r) { var a = o.hsvaToRgba(e.hsva);
                a.g = t / r, e.hsva = o.rgbaToHsva(a) }, e.setB = function(t, r) { var a = o.hsvaToRgba(e.hsva);
                a.b = t / r, e.hsva = o.rgbaToHsva(a) }, e.setSaturationAndBrightness = function(t, o, r, a) { e.hsva.s = t / r, e.hsva.v = o / a }, e.update = function() { var r = o.hsva2hsla(e.hsva);
                e.hslaText = { h: Math.round(360 * r.h), s: Math.round(100 * r.s), l: Math.round(100 * r.l), a: Math.round(100 * r.a) / 100 }; var a = t(o.hsvaToRgba(e.hsva)),
                    n = t(o.hsvaToRgba({ h: e.hsva.h, s: 1, v: 1, a: 1 })); if (e.rgbaText = { r: a.r, g: a.g, b: a.b, a: Math.round(100 * a.a) / 100 }, e.hexText = "#" + (1 << 24 | parseInt(a.r, 10) << 16 | parseInt(a.g, 10) << 8 | parseInt(a.b, 10)).toString(16).substr(1), e.hexText[1] === e.hexText[2] && e.hexText[3] === e.hexText[4] && e.hexText[5] === e.hexText[6] && (e.hexText = "#" + e.hexText[1] + e.hexText[3] + e.hexText[5]), e.alphaSliderColor = "rgb(" + a.r + "," + a.g + "," + a.b + ")", e.hueSliderColor = "rgb(" + n.r + "," + n.g + "," + n.b + ")", 0 === e.type && e.hsva.a < 1 && e.type++, e.hsva.a < 1) switch (e.colorPickerOutputFormat) {
                    case "hsla":
                        e.outputColor = "hsla(" + e.hslaText.h + "," + e.hslaText.s + "%," + e.hslaText.l + "%," + e.hslaText.a + ")"; break;
                    default:
                        e.outputColor = "rgba(" + a.r + "," + a.g + "," + a.b + "," + Math.round(100 * a.a) / 100 + ")" } else switch (e.colorPickerOutputFormat) {
                    case "hsla":
                        e.outputColor = "hsl(" + e.hslaText.h + "," + e.hslaText.s + "%," + e.hslaText.l + "%)"; break;
                    case "rgba":
                        e.outputColor = e.alphaSliderColor; break;
                    default:
                        e.outputColor = e.hexText }
                e.sAndLSlider = { left: e.hsva.s * e.sAndLMax.x - 8 + "px", top: (1 - e.hsva.v) * e.sAndLMax.y - 8 + "px" }, e.hueSlider.left = e.hsva.h * e.hueMax.x - 8 + "px", e.alphaSlider.left = e.hsva.a * e.alphaMax.x - 8 + "px", e.alphaInvalidClass = "" }, e.setColorFromHex = function(t) { var r = o.stringToHsva(t); return null !== r && (e.hsva = r), r }, e.typePolicy = function() { return e.type = (e.type + 1) % 3, 0 === e.type && e.hsva.a < 1 && e.type++, e.type } }], link: function(r, a, n) {
            function s(e) { var t = o.stringToHsva(e);
                null !== t && (r.hsva = t, r.update()) }

            function i() { setTimeout(function() { l() }, 5) }

            function l() { r.$apply(function() { n.colorPickerShowValue = "true", s(a.val()), r.colorPickerModel = a.val() }) }

            function c(t) { f = r.colorPickerModel, r.$apply(function() { r.show = !0 }), r.$apply(function() { r.sAndLMax = { x: g[0].getElementsByClassName("saturation-lightness")[0].offsetWidth, y: g[0].getElementsByClassName("saturation-lightness")[0].offsetHeight }, r.hueMax = { x: g[0].getElementsByClassName("hue")[0].offsetWidth }, r.alphaMax = { x: g[0].getElementsByClassName("alpha")[0].offsetWidth }, r.update() }), p(), e.on("mousedown", h), angular.element(window).on("resize", u) }

            function u() { p() }

            function p() { var e; "true" === n.colorPickerFixedPosition ? (e = v(a[0], !1), g[0].style.position = "fixed") : e = v(a[0], !0), "left" === n.colorPickerPosition ? (g[0].style.top = e.top + "px", g[0].style.left = e.left - 252 + "px") : "top" === n.colorPickerPosition ? (g[0].style.top = e.top - e.height - 284 + "px", g[0].style.left = e.left + "px") : "bottom" === n.colorPickerPosition ? (g[0].style.top = e.top + e.height + 10 + "px", g[0].style.left = e.left + "px") : (g[0].style.top = e.top + "px", g[0].style.left = e.left + e.width + "px") }

            function h(t) { t.target === a[0] || g[0] === t.target || d(g[0], t.target) || (r.$apply(function() { r.show = !1 }), e.off("mousedown", h), angular.element(window).off("resize", u)) }

            function d(e, t) { for (var o = t.parentNode; null !== o;) { if (o === e) return !0;
                    o = o.parentNode } return !1 }

            function v(e, t) { return { top: e.getBoundingClientRect().top + (t ? window.pageYOffset : 0), left: e.getBoundingClientRect().left + (t ? window.pageXOffset : 0), width: e.offsetWidth, height: e.offsetHeight } } var g, f = ""; if (void 0 === r.colorPickerModel && (r.colorPickerModel = "#008fff", a.val("")), void 0 === n.colorPickerShowValue && (n.colorPickerShowValue = "true"), void 0 === n.colorPickerPosition && (n.colorPickerPosition = "right"), void 0 === n.colorPickerShowInputSpinner && (n.colorPickerShowInputSpinner = "false"), void 0 === n.colorPickerShowCancelButton && (n.colorPickerShowCancelButton = "false"), "true" === n.colorPickerShowCancelButton && (r.showCancelButton = !0, r.extraLargeClass = "color-picker-extra-large"), void 0 !== n.colorPickerCancelButtonClass && (r.cancelButtonClass = n.colorPickerCancelButtonClass), void 0 !== n.colorPickerSpinnerRgbaSteps && null !== n.colorPickerSpinnerRgbaSteps.match(/^\d+;\d+;\d+;[0-9]+([\.][0-9]{1,2})?$/)) { var x = n.colorPickerSpinnerRgbaSteps.split(";");
                r.rbgaSteps = { r: x[0], g: x[1], b: x[2], a: x[3] } } if (void 0 !== n.colorPickerSpinnerHslaSteps && null !== n.colorPickerSpinnerHslaSteps.match(/^\d+;\d+;\d+;[0-9]+([\.][0-9]{1,2})?$/)) { var x = n.colorPickerSpinnerHslaSteps.split(";");
                r.hslaSteps = { h: x[0], s: x[1], l: x[2], a: x[3] } }
            s(r.colorPickerModel), "true" === n.colorPickerShowValue && a.val(r.outputColor), g = angular.element('<div ng-show="show" class="color-picker {{extraLargeClass}}"><div class="arrow arrow-' + n.colorPickerPosition + '"></div><div slider rg-x=1 rg-y=1 action="setSaturationAndBrightness(s, v, rgX, rgY)" class="saturation-lightness" ng-style="{\'background-color\':hueSliderColor}"><div class="cursor-sv" ng-style="{\'top\':sAndLSlider.top, \'left\':sAndLSlider.left}"></div></div><div slider rg-x=1 action="setHue(v, rg)" class="hue"><div class="cursor" ng-style="{\'left\':hueSlider.left}"></div></div><div slider rg-x=1 action="setAlpha(v, rg)" class="alpha" ng-style="{\'background-color\':alphaSliderColor}"><div class="cursor" ng-style="{\'left\':alphaSlider.left}"></div></div><div class="selected-color-background"></div><div class="selected-color" ng-style="{\'background-color\':outputColor}"></div><div ng-show="type==2" class="hsla-text"><input text type="number" pattern="[0-9]*" min="0" max="360" step="' + r.hslaSteps.h + '" rg=360 action="setHue(v, rg)" ng-model="hslaText.h" spinner="' + n.colorPickerShowInputSpinner + '" /><input text type="number" pattern="[0-9]*" min="0" max="100" step="' + r.hslaSteps.s + '" rg=100 action="setSaturation(v, rg)" ng-model="hslaText.s" spinner="' + n.colorPickerShowInputSpinner + '" /><input text type="number" pattern="[0-9]*" min="0" max="100" step="' + r.hslaSteps.l + '" rg=100 action="setLightness(v, rg)" ng-model="hslaText.l" spinner="' + n.colorPickerShowInputSpinner + '" /><input text type="number" pattern="[0-9]+([.,][0-9]{1,2})?" min="0" max="1" step="' + r.hslaSteps.a + '" rg=1 action="setAlpha(v, rg)" ng-model="hslaText.a" spinner="' + n.colorPickerShowInputSpinner + '" /><div>H</div><div>S</div><div>L</div><div>A</div></div><div ng-show="type==1" class="rgba-text"><input text type="number" pattern="[0-9]*" min="0" max="255" step="' + r.rbgaSteps.r + '" rg=255 action="setR(v, rg)" ng-model="rgbaText.r" spinner="' + n.colorPickerShowInputSpinner + '" /><input text type="number" pattern="[0-9]*" min="0" max="255" step="' + r.rbgaSteps.g + '" rg=255 action="setG(v, rg)" ng-model="rgbaText.g" spinner="' + n.colorPickerShowInputSpinner + '" /><input text type="number" pattern="[0-9]*" min="0" max="255" step="' + r.rbgaSteps.b + '" rg=255 action="setB(v, rg)" ng-model="rgbaText.b" spinner="' + n.colorPickerShowInputSpinner + '" /><input text type="number" pattern="[0-9]+([.,][0-9]{1,2})?" min="0" max="1" step="' + r.rbgaSteps.a + '" rg=1 action="setAlpha(v, rg)" ng-model="rgbaText.a" spinner="' + n.colorPickerShowInputSpinner + '" /><div>R</div><div>G</div><div>B</div><div>A</div></div><div class="hex-text" ng-show="type==0"><input text type="text" action="setColorFromHex(string)" ng-model="hexText"/><div>HEX</div></div><div ng-click="typePolicy()" class="type-policy"></div><button type="button" class="{{cancelButtonClass}}" ng-show="showCancelButton" ng-click="cancelColor()">Cancel</button></div>'), document.getElementsByTagName("body")[0].appendChild(g[0]), t(g)(r), a.on("paste", i), a.on("keyup", l), r.$on("color-changed", function(e) { r.$apply(function() { r.update(), r.colorPickerModel = r.outputColor, "true" === n.colorPickerShowValue && a.val(r.outputColor) }) }), r.cancelColor = function() { r.colorPickerModel = f, r.show = !1, s(r.colorPickerModel), e.off("mousedown", h), angular.element(window).off("resize", u) }, a.on("click", c), a.on("$destroy", function() { a.off("click", c), a.off("keyup", l), a.off("paste", i) }) } } }]), colorPicker.directive("text", [function() { return { restrict: "A", scope: { action: "&" }, link: function(e, t, o) {
            function r(e) { "true" === o.spinner && t.removeClass("color-picker-input-spinner") }

            function a(e) { t.addClass("color-picker-input-spinner") }

            function n(e) { setTimeout(function() { s(e) }, 5) }

            function s(r) { if (void 0 === o.rg) e.action({ string: t.val() }) && e.$emit("color-changed");
                else { var a = parseFloat(t.val());
                    isNaN(a) ? e.$emit("alpha-invalid") : (a = Math.abs(Math.min(parseFloat(t.val()), o.rg)), e.action({ v: a, rg: o.rg }), e.$emit("color-changed"), e.$emit("alpha-valid")) } }
            t.on("paste", n), t.on("keyup", s), t.on("change", s), t.on("focus", r), t.on("blur", a), t.on("mouseover", r), t.on("mouseout", a), t.addClass("color-picker-input-spinner"), t.on("$destroy", function() { t.off("paste", n), t.off("keyup", s), t.off("change", s), t.off("focus", r), t.off("blur", a), t.off("mouseover", r), t.off("mouseout", a) }) } } }]), colorPicker.directive("slider", ["$document", "$window", function(e, t) { return { restrict: "A", scope: { action: "&" }, link: function(o, r, a) {
            function n(t) { i(t), e.on("mousemove touchmove", s) }

            function s(e) { e.preventDefault(), i(e) }

            function i(e) { var t = r[0].offsetHeight,
                    n = r[0].offsetWidth,
                    s = Math.max(0, Math.min(l(e, r[0]), n)),
                    i = Math.max(0, Math.min(c(e, r[0]), t));
                void 0 !== a.rgX && void 0 !== a.rgY ? o.action({ s: s / n, v: 1 - i / t, rgX: a.rgX, rgY: a.rgY }) : o.action({ v: s / n, rg: a.rgX }), o.$emit("color-changed") }

            function l(e, o) { return (void 0 !== e.pageX ? e.pageX : e.touches[0].pageX) - o.getBoundingClientRect().left - t.pageXOffset }

            function c(e, o) { return (void 0 !== e.pageY ? e.pageY : e.touches[0].pageY) - o.getBoundingClientRect().top - t.pageYOffset }
            r.on("mousedown touchstart", n), e.on("mouseup touchend", function() { e.off("mousemove touchmove", s) }), r.on("$destroy", function() { r.off("mousedown touchend", n) }) } } }]);