package com.atm.sklepssm.baza.controller;

import com.atm.sklepssm.baza.domain.FakturyHasProdukty;
import com.atm.sklepssm.baza.facade.FakturyHasProduktyFacade;
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

@ManagedBean(name = "fakturyHasProduktyController")
@SessionScoped
public class FakturyHasProduktyController implements Serializable {

    private FakturyHasProdukty current;
    private DataModel items = null;
    @EJB
    private com.atm.sklepssm.baza.facade.FakturyHasProduktyFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public FakturyHasProduktyController() {
    }

    public FakturyHasProdukty getSelected() {
        if (current == null) {
            current = new FakturyHasProdukty();
            current.setFakturyHasProduktyPK(new com.atm.sklepssm.baza.domain.FakturyHasProduktyPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private FakturyHasProduktyFacade getFacade() {
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
        current = (FakturyHasProdukty) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new FakturyHasProdukty();
        current.setFakturyHasProduktyPK(new com.atm.sklepssm.baza.domain.FakturyHasProduktyPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            current.getFakturyHasProduktyPK().setFakturyFakturyId(current.getFaktury().getFakturyId());
            current.getFakturyHasProduktyPK().setProduktyProduktyId(current.getProdukty().getProduktyId());
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FakturyHasProduktyCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (FakturyHasProdukty) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            current.getFakturyHasProduktyPK().setFakturyFakturyId(current.getFaktury().getFakturyId());
            current.getFakturyHasProduktyPK().setProduktyProduktyId(current.getProdukty().getProduktyId());
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FakturyHasProduktyUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (FakturyHasProdukty) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("FakturyHasProduktyDeleted"));
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

    @FacesConverter(forClass = FakturyHasProdukty.class)
    public static class FakturyHasProduktyControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            FakturyHasProduktyController controller = (FakturyHasProduktyController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "fakturyHasProduktyController");
            return controller.ejbFacade.find(getKey(value));
        }

        com.atm.sklepssm.baza.domain.FakturyHasProduktyPK getKey(String value) {
            com.atm.sklepssm.baza.domain.FakturyHasProduktyPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new com.atm.sklepssm.baza.domain.FakturyHasProduktyPK();
            key.setFakturyFakturyId(Integer.parseInt(values[0]));
            key.setProduktyProduktyId(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(com.atm.sklepssm.baza.domain.FakturyHasProduktyPK value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value.getFakturyFakturyId());
            sb.append(SEPARATOR);
            sb.append(value.getProduktyProduktyId());
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof FakturyHasProdukty) {
                FakturyHasProdukty o = (FakturyHasProdukty) object;
                return getStringKey(o.getFakturyHasProduktyPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + FakturyHasProdukty.class.getName());
            }
        }
    }
}
