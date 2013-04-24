package com.atm.sklepssm.baza.controller;

import com.atm.sklepssm.baza.domain.ProduktyHasZamowienia;
import com.atm.sklepssm.baza.facade.ProduktyHasZamowieniaFacade;
import com.atm.sklepssm.baza.domain.util.JsfUtil;
import com.atm.sklepssm.baza.domain.util.PaginationHelper;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "produktyHasZamowieniaController")
@SessionScoped
public class ProduktyHasZamowieniaController implements Serializable {

    private ProduktyHasZamowienia current;
    private DataModel items = null;
    @EJB
    private com.atm.sklepssm.baza.facade.ProduktyHasZamowieniaFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public ProduktyHasZamowieniaController() {
    }

    public ProduktyHasZamowienia getSelected() {
        if (current == null) {
            current = new ProduktyHasZamowienia();
            current.setProduktyHasZamowieniaPK(new com.atm.sklepssm.baza.domain.ProduktyHasZamowieniaPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private ProduktyHasZamowieniaFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (ProduktyHasZamowienia) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ProduktyHasZamowienia();
        current.setProduktyHasZamowieniaPK(new com.atm.sklepssm.baza.domain.ProduktyHasZamowieniaPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getProduktyHasZamowieniaPK().setProduktyProduktyId(current.getProdukty().getProduktyId());
            current.getProduktyHasZamowieniaPK().setZamowieniaZamowieniaId(current.getZamowienia().getZamowieniaId());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProduktyHasZamowieniaCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (ProduktyHasZamowienia) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getProduktyHasZamowieniaPK().setProduktyProduktyId(current.getProdukty().getProduktyId());
            current.getProduktyHasZamowieniaPK().setZamowieniaZamowieniaId(current.getZamowienia().getZamowieniaId());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProduktyHasZamowieniaUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (ProduktyHasZamowienia) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ProduktyHasZamowieniaDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = ProduktyHasZamowienia.class)
    public static class ProduktyHasZamowieniaControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ProduktyHasZamowieniaController controller = (ProduktyHasZamowieniaController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "produktyHasZamowieniaController");
            return controller.ejbFacade.find(getKey(value));
        }

        com.atm.sklepssm.baza.domain.ProduktyHasZamowieniaPK getKey(String value) {
            com.atm.sklepssm.baza.domain.ProduktyHasZamowieniaPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.atm.sklepssm.baza.domain.ProduktyHasZamowieniaPK();
            key.setProduktyProduktyId(Integer.parseInt(values[0]));
            key.setZamowieniaZamowieniaId(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.atm.sklepssm.baza.domain.ProduktyHasZamowieniaPK value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value.getProduktyProduktyId());
            sb.append(SEPARATOR);
            sb.append(value.getZamowieniaZamowieniaId());
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof ProduktyHasZamowienia) {
                ProduktyHasZamowienia o = (ProduktyHasZamowienia) object;
                return getStringKey(o.getProduktyHasZamowieniaPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ProduktyHasZamowienia.class.getName());
            }
        }
    }
}
