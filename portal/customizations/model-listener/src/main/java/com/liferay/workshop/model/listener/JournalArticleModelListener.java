package com.liferay.workshop.model.listener;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import org.osgi.service.component.annotations.Component;

@Component(service = ModelListener.class)
public class JournalArticleModelListener extends BaseModelListener<JournalArticle> {

    @Override
    public void onAfterCreate(JournalArticle journalArticle) {
        System.out.println("Created new Journal Article with ID=" + journalArticle.getArticleId());
        super.onAfterCreate(journalArticle);
    }
}
