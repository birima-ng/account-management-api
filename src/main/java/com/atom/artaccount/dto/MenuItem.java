package com.atom.artaccount.dto;

import java.util.List;

public class MenuItem {
    private String path;
    private String title;
    private String icon;
    private String className;
    private String badge;
    private String badgeClass;
    private boolean isExternalLink;
    private List<MenuItem> submenu;

    // Getters and setters
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public boolean isExternalLink() {
        return isExternalLink;
    }

    public void setExternalLink(boolean externalLink) {
        isExternalLink = externalLink;
    }

    public List<MenuItem> getSubmenu() {
        return submenu;
    }

    public void setSubmenu(List<MenuItem> submenu) {
        this.submenu = submenu;
    }

	public String getBadge() {
		return badge;
	}

	public void setBadge(String badge) {
		this.badge = badge;
	}

	public String getBadgeClass() {
		return badgeClass;
	}

	public void setBadgeClass(String badgeClass) {
		this.badgeClass = badgeClass;
	}
}
