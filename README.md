## Welcome to DevWeb 

You can use the [editor on GitHub](https://github.com/imdadareeph/devweb/edit/master/README.md) to maintain and preview the content for your website in Markdown files.

~~Whenever you commit to this repository, take a pull request on your forked project.~~

### Framework

Sample custom component code

```markdown
public class MyCommandButtonRenderer extends CoreRenderer {

    @Override
	public void decode(FacesContext facesContext, UIComponent component) {
    	MyCommandButton button = (MyCommandButton) component;
        if(button.isDisabled()) {
            return;
        }
        
		String param = component.getClientId(facesContext);
		
		if(facesContext.getExternalContext().getRequestParameterMap().containsKey(param)) {
			component.queueEvent(new ActionEvent(component));
		}
	}
}
```
### Code structure

Develop and use custom component

### Support or Contact

@jamalsarfaraz and @imdadareeph - developers

Having trouble with Pages? Check out our [documentation](https://help.github.com/categories/github-pages-basics/) or [contact support](https://github.com/contact) and we’ll help you sort it out.
